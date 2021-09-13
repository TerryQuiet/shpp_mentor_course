package tk.quietdev.level1.repository

import kotlinx.coroutines.flow.Flow
import tk.quietdev.level1.models.UserModel
import tk.quietdev.level1.utils.DataState

interface Repository {
    fun updateUser(updatedUserModel: UserModel)
    fun addUser(userModel: UserModel): UserModel
    fun getUserWithNoValidation(id: Int): UserModel?
    fun getUserWithValidation(email: String, password: String): UserModel?
    fun getUserList(amount: Int = -1): List<UserModel>
    suspend fun userRegistration(login: String, password: String) : Flow<DataState<UserModel>>
    suspend fun userLogin(login: String, password: String) : Flow<DataState<UserModel>>
    fun currentUserFlow(): Flow<UserModel>
    fun getCurrentUserContactsFlow(): Flow<List<UserModel>>
    suspend fun cacheCurrentUserContactsFromApi()
    suspend fun cacheAllUsersFromApi()
    fun getAllUsersFlow(): Flow<List<UserModel>>
}