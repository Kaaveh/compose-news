package ir.kaaveh.baadbaadaknews.data.remote.khabaronline.dto

data class KhabarOnlineResponse(
    val rss: Rss?
) {
    data class Rss(
        val channel: Channel?,
    ) {
        data class Channel(
            val item: List<ItemDto>?,
        )
    }
}