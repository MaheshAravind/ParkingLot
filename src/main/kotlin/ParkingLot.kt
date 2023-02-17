import entities.ParkingReceipt
import entities.ParkingTicket
import models.ParkingSpots
import java.util.*
import java.util.concurrent.TimeUnit

class ParkingLot(capacity: Int = 100) {
    private var ticketNumber = 1
    private var receiptNumber = 1
    private val parkingSpots: ParkingSpots = ParkingSpots(capacity)

    fun park(entryDateTime: Date = Date()): ParkingTicket {
        val newTicketNumber = generateTicketNumber()
        val spotNumber = parkingSpots.findAndReserveFreeSpot()

        return ParkingTicket(newTicketNumber, spotNumber, entryDateTime)
    }

    fun unpark(ticket: ParkingTicket, exitDateTime: Date = Date()): ParkingReceipt {
        val newSpotNumber = ticket.getSpotNumber()
        parkingSpots.freeSpot(newSpotNumber)

        val newReceiptNumber = generateReceiptNumber()
        val entryDateTime = ticket.getEntryDateTime()
        val fee = calculateFee(entryDateTime, exitDateTime)

        return ParkingReceipt(newReceiptNumber, entryDateTime, exitDateTime, fee)
    }

    private fun calculateFee(entryDateTime: Date, exitDateTime: Date): Int {
        val timeDiffInMillis = exitDateTime.time - entryDateTime.time
        val durationInHours = TimeUnit.MILLISECONDS.toHours(timeDiffInMillis).toInt()
        val ratePerHour = 10

        return durationInHours * ratePerHour
    }

    private fun generateTicketNumber() = ticketNumber++
    private fun generateReceiptNumber() = receiptNumber++
}