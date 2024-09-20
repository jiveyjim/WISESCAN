package com.example.wisescan

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.io.InputStream
import java.nio.channels.Channels

class ModelManager(context: Context) {
    private val interpreter: Interpreter

    init {
        val modelPath = "model.tflite"
        val assetManager = context.assets
        val modelInputStream = assetManager.open(modelPath)
        val modelByteBuffer = loadModelFile(modelInputStream)
        interpreter = Interpreter(modelByteBuffer)
    }

    private fun loadModelFile(inputStream: InputStream): ByteBuffer {
        val fileChannel = Channels.newChannel(inputStream)
        val byteBuffer = ByteBuffer.allocateDirect(inputStream.available())
        byteBuffer.order(ByteOrder.nativeOrder())
        fileChannel.read(byteBuffer)
        byteBuffer.rewind()
        return byteBuffer
    }

    fun analyzeContent(input: ByteBuffer): ByteBuffer {
        val output = ByteBuffer.allocateDirect(4 * 10)
        output.order(ByteOrder.nativeOrder())
        interpreter.run(input, output)
        return output
    }
}
