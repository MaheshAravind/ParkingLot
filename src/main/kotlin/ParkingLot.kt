import entities.ParkingTicket
import java.util.*

class ParkingLot() {
    fun park(entryDateTime: Date = Date()): ParkingTicket {
        return ParkingTicket(1, 1, entryDateTime)
    }
}