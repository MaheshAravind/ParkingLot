import entities.ParkingReceipt
import entities.ParkingTicket
import models.ParkingSpots
import java.util.*

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
        val newReceiptNumber = generateReceiptNumber()
        val entryDateTime = ticket.getEntryDateTime()
        val fee = 10

        return ParkingReceipt(newReceiptNumber, entryDateTime, exitDateTime, fee)
    }

    private fun generateTicketNumber() = ticketNumber++
    private fun generateReceiptNumber() = receiptNumber++
}