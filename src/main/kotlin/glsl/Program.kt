package glsl

/**
 * Created by GBarbieri on 24.01.2017.
 */


import com.jogamp.common.net.Uri
import com.jogamp.opengl.GL2ES2.GL_FRAGMENT_SHADER
import com.jogamp.opengl.GL2ES2.GL_VERTEX_SHADER
import com.jogamp.opengl.GL3
import com.jogamp.opengl.GL3ES3.*
import com.jogamp.opengl.GLException
import com.jogamp.opengl.util.glsl.ShaderCode
import com.jogamp.opengl.util.glsl.ShaderProgram

/**

 * @author GBarbieri
 */
class Program {

    var name = 0
    val uniforms = HashMap<String, Int>()

    constructor(gl: GL3, shadersRoot: String, shadersSrc: String) : this(gl, shadersRoot, shadersSrc, shadersSrc)

    constructor(
            gl: GL3,
            shadersRoot: String,
            shadersSrc: String,
            replaceVertOld: Array<String>,
            replaceVertNew: Array<String>,
            replaceFragOld: Array<String>,
            replaceFragNew: Array<String>)

            : this(gl, shadersRoot, shadersSrc, shadersSrc, replaceVertOld, replaceVertNew, replaceFragOld, replaceFragNew)


    @JvmOverloads constructor(
            gl: GL3,
            shadersRoot: String,
            vertSrc: String,
            fragSrc: String,
            replaceVertOld: Array<String>? = null,
            replaceVertNew: Array<String>? = null,
            replaceFragOld: Array<String>? = null,
            replaceFragNew: Array<String>? = null) {

        val vertShader = ShaderCode.create(gl, GL_VERTEX_SHADER, this::class.java, shadersRoot, null, vertSrc, "vert", null, true)
        val fragShader = ShaderCode.create(gl, GL_FRAGMENT_SHADER, this::class.java, shadersRoot, null, fragSrc, "frag", null, true)

        if (replaceVertOld != null && replaceVertNew != null)
            repeat(replaceVertOld.size, { vertShader.replaceInShaderSource(replaceVertOld[it], replaceVertNew[it]) })

        if (replaceFragOld != null && replaceFragNew != null)
            repeat(replaceFragOld.size, { fragShader.replaceInShaderSource(replaceFragOld[it], replaceFragNew[it]) })

        val shaderProgram = ShaderProgram()

        shaderProgram.add(vertShader)
        shaderProgram.add(fragShader)

        shaderProgram.link(gl, System.err)

        vertShader.destroy(gl)
        fragShader.destroy(gl)

        name = shaderProgram.program()
    }

    constructor(gl: GL3, vertUri: Uri, fragUri: Uri) {

        val vertShader = ShaderCode.create(gl, GL_VERTEX_SHADER, 1, arrayOf(vertUri), false)
        val fragShader = ShaderCode.create(gl, GL_FRAGMENT_SHADER, 1, arrayOf(fragUri), false)

        val shaderProgram = ShaderProgram()

        shaderProgram.add(vertShader)
        shaderProgram.add(fragShader)

        shaderProgram.link(gl, System.err)

        vertShader.destroy(gl)
        fragShader.destroy(gl)

        name = shaderProgram.program()
    }

    constructor(gl: GL3, shaders: Array<String>, uniforms: Array<String> = emptyArray()) {

        val shaderProgram = ShaderProgram()

        val shaderCodes = shaders.map {
            ShaderCode.create(gl, it.type, 1, arrayOf(Uri.valueOf(this::class.java.classLoader.getResource(it))), false)
        }
        shaderCodes.forEach {
            try {
                shaderProgram.add(gl, it, System.err)
            } catch (ex: GLException) {
                System.err.println("shader $it")
            }
        }

        shaderProgram.link(gl, System.err)

        name = shaderProgram.program()

        shaderCodes.forEach { it.destroy(gl) }

        uniforms.forEach {
            val i = gl.glGetUniformLocation(name, it)
            if (i != -1)
                this.uniforms[it] = i
            else
                System.err.print("unable to find $it uniform location!")
        }
    }

    operator fun get(s: String) = uniforms[s]
}