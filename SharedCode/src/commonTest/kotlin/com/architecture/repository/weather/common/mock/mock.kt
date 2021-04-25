package com.architecture.repository.weather.common.mock

import com.architecture.repository.weather.model.WeatherModel
import com.architecture.repository.weather.service.WeatherService
import kotlinx.serialization.json.Json

class MockService : WeatherService {
    var exception: Throwable? = null


    override suspend fun getWeather(
        city: String,
        numberOfDate: String,
        appId: String,
        unit: String
    ): WeatherModel {
        if (exception == null) {
            val format = Json { ignoreUnknownKeys = true; isLenient = true }
            return format.decodeFromString<WeatherModel>(
                WeatherModel.serializer(),
                jsonData
            )
        } else {
            throw exception as Throwable
        }
    }
}

var jsonData = "{\n" +
        "  \"city\": {\n" +
        "    \"id\": 1580578,\n" +
        "    \"name\": \"Ho Chi Minh City\",\n" +
        "    \"coord\": {\n" +
        "      \"lon\": 106.6667,\n" +
        "      \"lat\": 10.8333\n" +
        "    },\n" +
        "    \"country\": \"VN\",\n" +
        "    \"population\": 0,\n" +
        "    \"timezone\": 25200\n" +
        "  },\n" +
        "  \"cod\": \"200\",\n" +
        "  \"message\": 3.2994377,\n" +
        "  \"cnt\": 7,\n" +
        "  \"list\": [\n" +
        "    {\n" +
        "      \"dt\": 1593316800,\n" +
        "      \"sunrise\": 1593297235,\n" +
        "      \"sunset\": 1593343144,\n" +
        "      \"temp\": {\n" +
        "        \"day\": 298.15,\n" +
        "        \"min\": 298.15,\n" +
        "        \"max\": 299.14,\n" +
        "        \"night\": 299.14,\n" +
        "        \"eve\": 298.15,\n" +
        "        \"morn\": 298.15\n" +
        "      },\n" +
        "      \"feels_like\": {\n" +
        "        \"day\": 302.11,\n" +
        "        \"night\": 302.8,\n" +
        "        \"eve\": 302.11,\n" +
        "        \"morn\": 302.11\n" +
        "      },\n" +
        "      \"pressure\": 1009,\n" +
        "      \"humidity\": 94,\n" +
        "      \"weather\": [\n" +
        "        {\n" +
        "          \"id\": 500,\n" +
        "          \"main\": \"Rain\",\n" +
        "          \"description\": \"light rain\",\n" +
        "          \"icon\": \"10d\"\n" +
        "        }\n" +
        "      ],\n" +
        "      \"speed\": 2.63,\n" +
        "      \"deg\": 197,\n" +
        "      \"clouds\": 75,\n" +
        "      \"rain\": 2.27\n" +
        "    },\n" +
        "    {\n" +
        "      \"dt\": 1593403200,\n" +
        "      \"sunrise\": 1593383649,\n" +
        "      \"sunset\": 1593429553,\n" +
        "      \"temp\": {\n" +
        "        \"day\": 303.88,\n" +
        "        \"min\": 298.82,\n" +
        "        \"max\": 304.44,\n" +
        "        \"night\": 298.82,\n" +
        "        \"eve\": 302.14,\n" +
        "        \"morn\": 299.1\n" +
        "      },\n" +
        "      \"feels_like\": {\n" +
        "        \"day\": 306.24,\n" +
        "        \"night\": 302.59,\n" +
        "        \"eve\": 305.09,\n" +
        "        \"morn\": 302.35\n" +
        "      },\n" +
        "      \"pressure\": 1009,\n" +
        "      \"humidity\": 64,\n" +
        "      \"weather\": [\n" +
        "        {\n" +
        "          \"id\": 502,\n" +
        "          \"main\": \"Rain\",\n" +
        "          \"description\": \"heavy intensity rain\",\n" +
        "          \"icon\": \"10d\"\n" +
        "        }\n" +
        "      ],\n" +
        "      \"speed\": 4.21,\n" +
        "      \"deg\": 221,\n" +
        "      \"clouds\": 95,\n" +
        "      \"rain\": 24.6\n" +
        "    },\n" +
        "    {\n" +
        "      \"dt\": 1593489600,\n" +
        "      \"sunrise\": 1593470063,\n" +
        "      \"sunset\": 1593515960,\n" +
        "      \"temp\": {\n" +
        "        \"day\": 302.68,\n" +
        "        \"min\": 298.58,\n" +
        "        \"max\": 303.31,\n" +
        "        \"night\": 299.45,\n" +
        "        \"eve\": 302.51,\n" +
        "        \"morn\": 298.58\n" +
        "      },\n" +
        "      \"feels_like\": {\n" +
        "        \"day\": 306.45,\n" +
        "        \"night\": 302.88,\n" +
        "        \"eve\": 306.23,\n" +
        "        \"morn\": 303.08\n" +
        "      },\n" +
        "      \"pressure\": 1009,\n" +
        "      \"humidity\": 69,\n" +
        "      \"weather\": [\n" +
        "        {\n" +
        "          \"id\": 501,\n" +
        "          \"main\": \"Rain\",\n" +
        "          \"description\": \"moderate rain\",\n" +
        "          \"icon\": \"10d\"\n" +
        "        }\n" +
        "      ],\n" +
        "      \"speed\": 2.29,\n" +
        "      \"deg\": 279,\n" +
        "      \"clouds\": 89,\n" +
        "      \"rain\": 7.71\n" +
        "    },\n" +
        "    {\n" +
        "      \"dt\": 1593576000,\n" +
        "      \"sunrise\": 1593556478,\n" +
        "      \"sunset\": 1593602368,\n" +
        "      \"temp\": {\n" +
        "        \"day\": 303.33,\n" +
        "        \"min\": 297.88,\n" +
        "        \"max\": 305.64,\n" +
        "        \"night\": 300.06,\n" +
        "        \"eve\": 303.33,\n" +
        "        \"morn\": 297.88\n" +
        "      },\n" +
        "      \"feels_like\": {\n" +
        "        \"day\": 304.86,\n" +
        "        \"night\": 302.45,\n" +
        "        \"eve\": 306.05,\n" +
        "        \"morn\": 301.4\n" +
        "      },\n" +
        "      \"pressure\": 1008,\n" +
        "      \"humidity\": 61,\n" +
        "      \"weather\": [\n" +
        "        {\n" +
        "          \"id\": 500,\n" +
        "          \"main\": \"Rain\",\n" +
        "          \"description\": \"light rain\",\n" +
        "          \"icon\": \"10d\"\n" +
        "        }\n" +
        "      ],\n" +
        "      \"speed\": 4.38,\n" +
        "      \"deg\": 277,\n" +
        "      \"clouds\": 66,\n" +
        "      \"rain\": 2.78\n" +
        "    },\n" +
        "    {\n" +
        "      \"dt\": 1593662400,\n" +
        "      \"sunrise\": 1593642893,\n" +
        "      \"sunset\": 1593688775,\n" +
        "      \"temp\": {\n" +
        "        \"day\": 303.44,\n" +
        "        \"min\": 298.34,\n" +
        "        \"max\": 305.06,\n" +
        "        \"night\": 299.73,\n" +
        "        \"eve\": 303.66,\n" +
        "        \"morn\": 298.34\n" +
        "      },\n" +
        "      \"feels_like\": {\n" +
        "        \"day\": 305.14,\n" +
        "        \"night\": 302.6,\n" +
        "        \"eve\": 305.76,\n" +
        "        \"morn\": 301.42\n" +
        "      },\n" +
        "      \"pressure\": 1008,\n" +
        "      \"humidity\": 65,\n" +
        "      \"weather\": [\n" +
        "        {\n" +
        "          \"id\": 501,\n" +
        "          \"main\": \"Rain\",\n" +
        "          \"description\": \"moderate rain\",\n" +
        "          \"icon\": \"10d\"\n" +
        "        }\n" +
        "      ],\n" +
        "      \"speed\": 5.03,\n" +
        "      \"deg\": 256,\n" +
        "      \"clouds\": 63,\n" +
        "      \"rain\": 9.42\n" +
        "    },\n" +
        "    {\n" +
        "      \"dt\": 1593748800,\n" +
        "      \"sunrise\": 1593729308,\n" +
        "      \"sunset\": 1593775181,\n" +
        "      \"temp\": {\n" +
        "        \"day\": 303.04,\n" +
        "        \"min\": 299.28,\n" +
        "        \"max\": 304.12,\n" +
        "        \"night\": 299.32,\n" +
        "        \"eve\": 302.14,\n" +
        "        \"morn\": 299.28\n" +
        "      },\n" +
        "      \"feels_like\": {\n" +
        "        \"day\": 305.32,\n" +
        "        \"night\": 302.7,\n" +
        "        \"eve\": 304.07,\n" +
        "        \"morn\": 302.82\n" +
        "      },\n" +
        "      \"pressure\": 1009,\n" +
        "      \"humidity\": 68,\n" +
        "      \"weather\": [\n" +
        "        {\n" +
        "          \"id\": 501,\n" +
        "          \"main\": \"Rain\",\n" +
        "          \"description\": \"moderate rain\",\n" +
        "          \"icon\": \"10d\"\n" +
        "        }\n" +
        "      ],\n" +
        "      \"speed\": 4.5,\n" +
        "      \"deg\": 254,\n" +
        "      \"clouds\": 100,\n" +
        "      \"rain\": 10.95\n" +
        "    },\n" +
        "    {\n" +
        "      \"dt\": 1593835200,\n" +
        "      \"sunrise\": 1593815723,\n" +
        "      \"sunset\": 1593861586,\n" +
        "      \"temp\": {\n" +
        "        \"day\": 301.47,\n" +
        "        \"min\": 298.48,\n" +
        "        \"max\": 301.47,\n" +
        "        \"night\": 298.48,\n" +
        "        \"eve\": 299.13,\n" +
        "        \"morn\": 298.53\n" +
        "      },\n" +
        "      \"feels_like\": {\n" +
        "        \"day\": 305.28,\n" +
        "        \"night\": 302.26,\n" +
        "        \"eve\": 302.82,\n" +
        "        \"morn\": 302.96\n" +
        "      },\n" +
        "      \"pressure\": 1011,\n" +
        "      \"humidity\": 73,\n" +
        "      \"weather\": [\n" +
        "        {\n" +
        "          \"id\": 501,\n" +
        "          \"main\": \"Rain\",\n" +
        "          \"description\": \"moderate rain\",\n" +
        "          \"icon\": \"10d\"\n" +
        "        }\n" +
        "      ],\n" +
        "      \"speed\": 2.05,\n" +
        "      \"deg\": 225,\n" +
        "      \"clouds\": 100,\n" +
        "      \"rain\": 5.86\n" +
        "    }\n" +
        "  ]\n" +
        "}"