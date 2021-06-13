package com.mindbody.countries.data

class Repository(private val remoteDataSource: DataSource) : DataSource {

    companion object {

        private var sINSTANCE: Repository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.

         * @param remoteDataSource the backend data source
         *
         * @return the [Repository] instance
         */
        @JvmStatic
        fun getInstance(remoteDataSource: DataSource) =
            sINSTANCE ?: synchronized(Repository::class.java) {
                sINSTANCE ?: Repository(remoteDataSource)
                    .also { sINSTANCE = it }
            }


        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            sINSTANCE = null
        }
    }

    override fun getCountries(callback: DataSource.GetCountriesCallback) {
        remoteDataSource.getCountries(callback)
    }

    override fun getProvinces(countryId: Int, callback: DataSource.GetProvincesCallback) {
        remoteDataSource.getProvinces(countryId, callback)
    }
}
