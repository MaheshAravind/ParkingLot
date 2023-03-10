import entities.ParkingReceipt
import entities.ParkingTicket
import exceptions.SpotAlreadyFreeException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertIs

class ParkingLotTest{
    @Test
    fun `should be able to park`() {
        val parkingLot = ParkingLot()

        assertDoesNotThrow { parkingLot.park() }
    }

    @Test
    fun `should get parking ticket after parking`() {
        val parkingLot = ParkingLot()

        val parkingTicket = parkingLot.park()

        assertIs<ParkingTicket>(parkingTicket)
    }

    @Test
    fun `should get parking ticket with proper format`() {
        val parkingLot = ParkingLot()
        val date = Date(1676410567394)
        val expected =
            """Parking Ticket:
    Ticket Number: 1
    Spot Number: 1
    Entry Date-time: Wed Feb 15 03:06:07 IST 2023
        """.trimIndent()

        val parkingTicket = parkingLot.park(date)

        assertEquals(expected, parkingTicket.toString())
    }

    @Test
    fun `should be able to unpark after parking`() {
        val parkingLot = ParkingLot()
        val parkingTicket = parkingLot.park()

        assertDoesNotThrow { parkingLot.unpark(parkingTicket) }
    }

    @Test
    fun `should get parking receipt after unparking`() {
        val parkingLot = ParkingLot()
        val parkingTicket = parkingLot.park()

        val parkingReceipt = parkingLot.unpark(parkingTicket)

        assertIs<ParkingReceipt>(parkingReceipt)
    }

    @Test
    fun `should get parking receipt with proper format`() {
        val parkingLot = ParkingLot()
        val entryDateTime = Date(1676410567394)
        val parkingTicket = parkingLot.park(entryDateTime)
        val exitDateTime = Date(1676410567394 + 1000 * 3600 * 2)
        val expected =
            """Parking Receipt:
    Receipt Number: 1
    Entry Date-time: Wed Feb 15 03:06:07 IST 2023
    Exit Date-time: Wed Feb 15 05:06:07 IST 2023
    Fees: 20"""

        val parkingReceipt = parkingLot.unpark(parkingTicket, exitDateTime)

        assertEquals(expected, parkingReceipt.toString())
    }

    @Test
    fun `should get parking receipt with correct fee amount`() {
        val durationInHours = 5
        val ratePerHour = 10

        val parkingLot = ParkingLot()
        val entryDateTime = Date(1676410567394)
        val parkingTicket = parkingLot.park(entryDateTime)
        val exitDateTime = Date(1676410567394 + 1000 * 3600 * durationInHours)
        val expectedFee = durationInHours * ratePerHour
        val expected = ParkingReceipt(1, entryDateTime, exitDateTime, expectedFee)

        val parkingReceipt = parkingLot.unpark(parkingTicket, exitDateTime)

        assertEquals(expected, parkingReceipt)
    }

    @Test
    fun `should not be able to unpark from a spot that was not parked on`() {
        val parkingLot = ParkingLot()
        val fakeParkingTicket = ParkingTicket(1, 1, Date())

        assertThrows(SpotAlreadyFreeException::class.java) { parkingLot.unpark(fakeParkingTicket) }
    }

    @Test
    fun `should not be able to unpark from a spot that does not exist`() {
        val parkingLot = ParkingLot(1)
        val fakeParkingTicket = ParkingTicket(1, 2, Date())

        assertThrows(SpotAlreadyFreeException::class.java) { parkingLot.unpark(fakeParkingTicket) }
    }
}