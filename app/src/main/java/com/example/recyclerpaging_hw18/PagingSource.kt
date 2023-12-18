package com.example.recyclerpaging_hw18

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.recyclerpaging_hw18.model.User
import com.example.recyclerpaging_hw18.service.UserService
import okio.IOException
import retrofit2.HttpException

class PagingSource(
    private val userService: UserService,
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = userService.getUsers(nextPageNumber)

            LoadResult.Page(
                data = response.body()?.data ?: emptyList(),
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (nextPageNumber < (response.body()?.totalPages
                        ?: 1)
                ) nextPageNumber + 1 else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}