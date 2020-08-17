package com.marvelcomics.brito.data.repository.comics

import com.marvelcomics.brito.data.entity.ComicEntity
import com.marvelcomics.brito.data.handler.ResourceModel

interface ComicRepository {
    suspend fun comics(characterId: Int): ResourceModel<List<ComicEntity>>
}