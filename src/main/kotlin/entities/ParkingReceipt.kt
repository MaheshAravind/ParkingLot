package entities

import java.util.*

data class ParkingReceipt(
    private val receiptNumber: Int,
    private val entryDateTime: Date,
    private val exitDateTime: Date,
    private val fee: Int
) {
    override fun toString(): String {
        return """
            Parking Receipt:
                Receipt Number: $receiptNumber
                Entry Date-time: $entryDateTime
                Exit Date-time: $exitDateTime
                Fees: $fee
        """.trimIndent()
    }
}