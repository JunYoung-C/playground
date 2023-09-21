data class User(
    val userId: Long,
    var email: String,
    var type: UserType,
    var isEmailConfirmed: Boolean,
) {
    fun changeEmail(newEmail: String, company: Company) {
        if (email == newEmail) {
            return
        }

        val newType: UserType = if (company.isEmailCorporate(newEmail)) {
            UserType.Employee
        } else {
            UserType.Customer
        }

        if (type != newType) {
            val delta = if (newType == UserType.Employee) {
                1
            } else {
                -1
            }

            company.changeNumberOfEmployees(delta)
        }

        email = newEmail
        type = newType
    }

    enum class UserType {
        Employee, Customer
    }

}