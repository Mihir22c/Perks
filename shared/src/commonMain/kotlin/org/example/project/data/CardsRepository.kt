package org.example.project.data

import com.liftric.kvault.KVault
import kotlinx.coroutines.flow.Flow
import org.example.project.Card
import org.example.project.data.local.CardsDao
import kotlin.random.Random

class CardsRepository(
    private val dao: CardsDao,
    private val secureStorage: KVault,
) {
    fun observeCards(): Flow<List<Card>> = dao.observeAll()

    fun addCard(name: String, number: String) {
        val id = Random.nextLong()                  // local id
        dao.insert(Card(id, name))                  // name → DB
        secureStorage.set(key = numberKey(id), stringValue = number)  // number → encrypted
    }

    fun getCardNumber(id: Long): String? =
        secureStorage.string(forKey = numberKey(id))

    fun deleteCard(id: Long) {
        dao.delete(id)
        secureStorage.deleteObject(forKey = numberKey(id))
    }

    private fun numberKey(id: Long) = "card_number_$id"
}