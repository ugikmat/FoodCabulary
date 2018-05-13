package id.filkom.mat.foodcab.model

/**
 * Created by mat on 5/13/18.
 */
class Kategori {
    companion object Factory {
        fun create(): Kategori = Kategori()
    }
    var name: String? = null
    var image: Int? = null
}