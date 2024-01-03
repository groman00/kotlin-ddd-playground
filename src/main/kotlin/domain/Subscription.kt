package domain

import java.time.Instant
import java.util.UUID

sealed interface Subscription {
    val id: UUID
}

data class InactiveSubscription(
    override val id: UUID
) : Subscription {
    fun activate(activatedOn: Instant): ActiveSubscription {
        return ActiveSubscription(id, activatedOn)
    }
}

// See ActiveSubscription.kt for example using extension functions.
data class ActiveSubscription(
    override val id: UUID,
    val activatedOn: Instant,
) : Subscription

data class PausedSubscription(
    override val id: UUID
) : Subscription {
    fun resume(activatedOn: Instant): ActiveSubscription {
        return ActiveSubscription(id, activatedOn)
    }

    fun cancel(): CancelledSubscription {
        return CancelledSubscription(id)
    }
}

data class CancelledSubscription(
    override val id: UUID
) : Subscription {
    fun resume(activatedOn: Instant): ActiveSubscription {
        return ActiveSubscription(id, activatedOn)
    }
}
