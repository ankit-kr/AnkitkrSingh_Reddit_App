package com.ankit.ankitkrsingh_reddit.di

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

/**
 *<h1></h1>

 *<p></p>

 * @author : Ankit
 * @since : 16 Dec 2019
 * @version : 1.0
 *
 */
class AppTypeAdapterFactory private constructor() : TypeAdapterFactory{

    companion object{
        @JvmStatic
        fun newInstance(): TypeAdapterFactory {
            return AppTypeAdapterFactory()
        }
    }
    override fun <T : Any?> create(gson: Gson, type: TypeToken<T>?): TypeAdapter<T> {
        val delegate: TypeAdapter<T> = gson.getDelegateAdapter(this, type)
        val elementAdapter: TypeAdapter<JsonElement> = gson.getAdapter(JsonElement::class.java)
        return AppTypeAdapter(delegate, elementAdapter).nullSafe()
    }



    class AppTypeAdapter<T> constructor(
        val delegate: TypeAdapter<T>,
        val elementAdapter : TypeAdapter<JsonElement>
    ): TypeAdapter<T>(){

        private val RESPONSE_TAG_DATA = "data"
        private val RESPONSE_TAG_CHIDREN = "children"

        override fun write(out: JsonWriter?, value: T) {
            delegate.write(out, value)
        }

        override fun read(`in`: JsonReader?): T {
            val jsonElement = elementAdapter.read(`in`)
            if (jsonElement.isJsonObject) {
                // validateResponse(response)
                return getResponse(jsonElement.asJsonObject)
            }
            return delegate.fromJsonTree(jsonElement)
        }


        // map data according to tag
        private fun getResponse(jsonObject: JsonObject): T {
            return if (jsonObject.has(RESPONSE_TAG_DATA) && jsonObject.get(RESPONSE_TAG_DATA).isJsonObject && jsonObject.get(RESPONSE_TAG_DATA).asJsonObject.has(
                    RESPONSE_TAG_CHIDREN)) {
                delegate.fromJsonTree(jsonObject.get(RESPONSE_TAG_DATA).asJsonObject.get(RESPONSE_TAG_CHIDREN))
            } else if (jsonObject.has(RESPONSE_TAG_DATA)) {
                delegate.fromJsonTree(jsonObject.get(RESPONSE_TAG_DATA))
            } else {
                delegate.fromJsonTree(jsonObject)
            }
        }
    }

}