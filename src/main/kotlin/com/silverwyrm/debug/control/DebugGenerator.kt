package com.silverwyrm.debug.control

import com.silverwyrm.nickname.control.NicknameDao
import com.silverwyrm.nickname.entity.Nickname
import com.silverwyrm.person.control.PersonDao
import com.silverwyrm.person.entity.Person
import com.silverwyrm.quote.control.QuoteDao
import com.silverwyrm.quote.entity.Quote
import com.silverwyrm.user.control.UserDao
import com.silverwyrm.user.entity.QuoteUser
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional

@ApplicationScoped
open class DebugGenerator {

    @Inject
    open lateinit var quoteDao: QuoteDao

    @Inject
    open lateinit var personDao: PersonDao

    @Inject
    open lateinit var userDao: UserDao

    @Inject
    open lateinit var nicknameDao: NicknameDao

    @Transactional
    open fun generateSomeData(){


        val jan = Person().apply { firstName="Jan"; lastName="Neubauer"; nicknames=listOf(Nickname().apply { text="Der Denker" }, Nickname().apply { text="Big Brain Dude" }) }
        val erik = QuoteUser().apply { firstName="Erik"; lastName="Mayerhofer"; sub="eriksGoogleSub"; nicknames=listOf(Nickname().apply { text="Der Privatdetektiv" }) }
        val wahli = Person().apply { firstName="Max"; lastName="Wal"; nicknames=listOf(Nickname().apply { text="Raketenkopf" })  }

        jan.nicknames.forEach { nicknameDao.persist(it) }
        erik.nicknames.forEach { nicknameDao.persist(it) }
        wahli.nicknames.forEach { nicknameDao.persist(it) }

        personDao.add(jan)
        userDao.add(erik)
        personDao.add(wahli)

        val quotes = arrayOf(
                Quote().apply { text="Kinan wir ned afoch getBlob() mochn?"; quotedPersons=listOf(jan); quoter=erik },
                Quote().apply { text="E: Du bist böse wast du des?\nW: Jo."; quotedPersons=listOf(erik, wahli); quoter=erik }
        )
        quotes.forEach { quoteDao.add(it) }
    }
}