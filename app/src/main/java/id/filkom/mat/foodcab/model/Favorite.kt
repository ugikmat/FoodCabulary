package id.filkom.mat.foodcab.model

/**
 * Created by mat on 5/13/18.
 */
class Favorite {
    companion object Factory {
        fun create(): Favorite = Favorite()
    }
    var id: String? = null
    var name: String? = null
    var category: String? = null
    var image: String? = null
    var location: String? = null
    var seller: String? = null
    var phone: String? =null
}