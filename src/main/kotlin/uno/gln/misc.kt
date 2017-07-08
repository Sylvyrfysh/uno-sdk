package uno.gln

import android.opengl.GLES20
import glm_.f
import glm_.set
import glm_.vec2.Vec2i
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import uno.buffer.doubleBufferBig
import uno.buffer.floatBufferBig
import uno.buffer.intBufferBig

/**
 * Created by elect on 18/04/17.
 */


//fun glClearBuffer(buffer: Int, value: Float) = glClearBuffer(buffer, 0, value)
//
//fun glClearBuffer(buffer: Int, drawbuffer: Int, value: Float) {
//    floatBuffer[0] = value
//    glClearBufferfv(buffer, drawbuffer, floatBuffer)
//}
//
//fun glClearBuffer(buffer: Int, value: Vec4) = glClearBuffer(buffer, 0, value)
//fun glClearBuffer(buffer: Int, drawbuffer: Int, value: Vec4) = glClearBufferfv(buffer, drawbuffer, value to mat4Buffer)

fun glViewport(width: Int, height: Int) = GLES20.glViewport(0, 0, width, height)

fun glViewport(size: Vec2i) = GLES20.glViewport(0, 0, size.x, size.y)

//fun glBlitFramebuffer(size: Vec2i) = glBlitFramebuffer(
//        0, 0, size.x, size.y,
//        0, 0, size.x, size.y,
//        GL11.GL_COLOR_BUFFER_BIT, GL11.GL_LINEAR)


fun glClearColor() = GLES20.glClearColor(0f, 0f, 0f, 1f)
fun glClearColor(float: Float) = GLES20.glClearColor(float, float, float, float)
fun glClearColor(number: Number) = GLES20.glClearColor(number.f, number.f, number.f, number.f)
fun glClearDepthf() = GLES20.glClearDepthf(1f)


val floatBuffer = floatBufferBig(1)
val intBuffer = intBufferBig(1)

val vec2Buffer = floatBufferBig(2)
val vec3Buffer = floatBufferBig(3)
val vec4Buffer = floatBufferBig(4)

val mat2Buffer = floatBufferBig(2 * 2)
val mat2x3Buffer = floatBufferBig(2 * 3)
val mat2x4Buffer = floatBufferBig(2 * 4)
val mat3x2Buffer = floatBufferBig(3 * 2)
val mat3Buffer = floatBufferBig(3 * 3)
val mat3x4Buffer = floatBufferBig(3 * 4)
val mat4x2Buffer = floatBufferBig(4 * 2)
val mat4x3Buffer = floatBufferBig(4 * 3)
val mat4Buffer = floatBufferBig(4 * 4)

val mat2dBuffer = doubleBufferBig(2 * 2)
val mat2x3dBuffer = doubleBufferBig(2 * 3)
val mat2x4dBuffer = doubleBufferBig(2 * 4)
val mat3x2dBuffer = doubleBufferBig(3 * 2)
val mat3dBuffer = doubleBufferBig(3 * 3)
val mat3x4dBuffer = doubleBufferBig(3 * 4)
val mat4x2dBuffer = doubleBufferBig(4 * 2)
val mat4x3dBuffer = doubleBufferBig(4 * 3)
val mat4dBuffer = doubleBufferBig(4 * 4)


fun glVertexAttrib3(indx: Int, vec: Vec3) = GLES20.glVertexAttrib3f(indx, vec.x, vec.y, vec.z)
fun glVertexAttrib3(indx: Int, vec: Vec4) = GLES20.glVertexAttrib3f(indx, vec.x, vec.y, vec.z)