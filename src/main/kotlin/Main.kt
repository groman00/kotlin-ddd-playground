import domain.InactiveSubscription
import repository.SubscriptionRepository
import java.time.Instant

fun main(args: Array<String>) {

    val subscriptionRepository = SubscriptionRepository()

    // Create a new subscription.
    val subscription = subscriptionRepository.create()

    if (subscription is InactiveSubscription) {
        println("Sub is inactive $subscription")

        // Activate subscription
        val activeSubscription = subscription.activate(Instant.now())

        println("Sub is active $activeSubscription")

        // Save subscription
        subscriptionRepository.save(activeSubscription)

        /*
            Example Output:
            Sub is inactive InactiveSubscription(id=212b32fc-c488-4ff6-a34b-acf468a31b1d)
            Sub is active ActiveSubscription(id=212b32fc-c488-4ff6-a34b-acf468a31b1d, activatedOn=2024-01-03T21:13:57.920172Z)
            Save to db SubscriptionRecord(id=212b32fc-c488-4ff6-a34b-acf468a31b1d, status=ACTIVE, activatedOn=2024-01-03T21:13:57.920172Z, cancelledOn=null, pausedOn=null)
         */
    }
}