package id.filkom.mat.foodcab.model

/**
 * Created by mat on 5/13/18.
 */
object Users {
    val user: User?
    init {
        user = User.create()
    }
}