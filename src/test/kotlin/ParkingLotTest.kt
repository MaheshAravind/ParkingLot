import entities.ParkingTicket
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertIs

class ParkingLotTest{
    @Test
    fun `should be able to park`() {
        val parkingLot = ParkingLot()

        assertDoesNotThrow{parkingLot.park()}
    }

    @Test
    fun `should get parking ticket after parking`() {
        val parkingLot = ParkingLot()
        val parkingTicket = parkingLot.park()

        assertIs<ParkingTicket>(parkingTicket)
    }
}