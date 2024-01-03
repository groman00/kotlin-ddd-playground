package db

import java.time.Instant
import java.util.UUID

data class SubscriptionRecord(
    val id: UUID,
    val status: Status,
    val activatedOn: Instant?,
    val cancelledOn: Instant?,
    val pausedOn: Instant?,
)