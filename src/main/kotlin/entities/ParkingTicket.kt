package entities

import java.util.*

data class ParkingTicket(
    private val ticketNumber: Int,
    private val spotNumber: Int,
    private val entryDateTime: Date
) {
    override fun toString(): String {
        return """
            Parking Ticket:
                Ticket Number: $ticketNumber
                Spot Number: $spotNumber
                Entry Date-time: $entryDateTime
        """.trimIndent()
    }

    fun getSpotNumber() = ticketNumber
    fun getEntryDateTime() = entryDateTime
}
