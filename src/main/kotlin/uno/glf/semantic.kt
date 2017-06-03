package uno.glf

/**
 * Created by GBarbieri on 27.03.2017.
 */

object semantic {

    object buffer {
        var STATIC = 0
        var DYNAMIC = 1
    }

    object uniform {
        var MATERIAL = 0
        var TRANSFORM0 = 1
        var TRANSFORM1 = 2
        var INDIRECTION = 3
        var CONSTANT = 0
        var PER_FRAME = 1
        var PER_PASS = 2
        var LIGHT = 3
    }

    object sampler {
        var DIFFUSE = 0
        var SPECULAR = 1
    }

    object image {
        var DIFFUSE = 0
        var PICKING = 1
    }

    object attr {
        var POSITION = 0
        var NORMAL = 1
        var COLOR = 3
        var TEX_COORD = 4
        var DRAW_ID = 5
    }

    object vert {
        var POSITION = 0
        var COLOR = 3
        var TEXCOORD = 4
        var INSTANCE = 7
    }

    object frag {
        var COLOR = 0
        var RED = 0
        var GREEN = 1
        var BLUE = 2
        var ALPHA = 0
    }

    object renderbuffer {
        var DEPTH = 0
        var COLOR0 = 1
    }

    object storage {
        var VERTEX = 0
    }
}
