package converters

import db.Status
import db.SubscriptionRecord
import domain.*

class SubscriptionConverter {

    fun convertToRecord(subscription: Subscription): SubscriptionRecord {
        return when (subscription) {
            is ActiveSubscription -> SubscriptionRecord(
                id = subscription.id,
                status = Status.ACTIVE,
                activatedOn = subscription.activatedOn,
                cancelledOn = null,
                pausedOn = null,
            )
            is CancelledSubscription -> SubscriptionRecord(
                id = subscription.id,
                status = Status.CANCELLED,
                activatedOn = null,
                cancelledOn = null,
                pausedOn = null,
            )
            is InactiveSubscription -> SubscriptionRecord(
                id = subscription.id,
                status = Status.INACTIVE,
                activatedOn = null,
                cancelledOn = null,
                pausedOn = null,
            )
            is PausedSubscription -> SubscriptionRecord(
                id = subscription.id,
                status = Status.PAUSED,
                activatedOn = null,
                cancelledOn = null,
                pausedOn = null,
            )
            else -> throw IllegalArgumentException("Invalid subscription: $subscription")
        }
    }

    fun convertToDomain(record: SubscriptionRecord): Subscription {
        return when (record.status) {
            Status.ACTIVE -> ActiveSubscription(record.id, record.activatedOn!!) // Should validate / throw error if no activatedOn.
            Status.CANCELLED -> CancelledSubscription(record.id)
            Status.INACTIVE -> InactiveSubscription(record.id)
            Status.PAUSED -> PausedSubscription(record.id)
            else -> throw IllegalArgumentException("Invalid status: ${record.status}")
        }
    }
}