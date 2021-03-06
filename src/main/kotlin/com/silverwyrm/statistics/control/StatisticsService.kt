package com.silverwyrm.statistics.control

import com.silverwyrm.nickname.control.NicknameDao
import com.silverwyrm.person.control.PersonDao
import com.silverwyrm.statistics.entity.PersonStatDto
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.persistence.EntityManager

@ApplicationScoped
open class StatisticsService {

    @Inject
    open lateinit var entityManager: EntityManager

    open fun getTopPeople(): List<PersonStatDto> {
        val query = entityManager.createNamedQuery("person.countQuotes", PersonStatDto::class.java)
        return query.resultList
    }
}