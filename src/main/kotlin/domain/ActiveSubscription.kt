package domain

fun ActiveSubscription.pause(): PausedSubscription {
    return PausedSubscription(id)
}

fun ActiveSubscription.cancel(): CancelledSubscription {
    return CancelledSubscription(id)
}