package com.origogi.dailypulse.articles

import kotlin.uuid.Uuid

data class Article(
    val title : String,
    val description : String,
    val date : String,
    val imageUrl : String,

)