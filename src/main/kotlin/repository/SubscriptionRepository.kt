package repository

import converters.SubscriptionConverter
import db.Status
import db.SubscriptionRecord
import domain.Subscription
import java.time.Instant
import java.util.*

class SubscriptionRepository {
    private val subscriptionConverter = SubscriptionConverter()

    fun create(): Subscription {
        val subscriptionRecord = SubscriptionRecord(
            id = UUID.randomUUID(),
            status = Status.INACTIVE,
            activatedOn = Instant.now(),
            cancelledOn = null,
            pausedOn = null,
        )
        return subscriptionConverter.convertToDomain(subscriptionRecord)
    }

    fun save(subscription: Subscription): Unit {
        val subscriptionRecord = subscriptionConverter.convertToRecord(subscription)
        println("Save to db $subscriptionRecord")
    }
}