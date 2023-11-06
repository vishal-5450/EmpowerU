package com.example.root_food_master.model

data class NGODataModelFirebase(
    var commitment: String = "",
    var default_currency: String = "",
    var description: String = "",
    var disbursement: String = "",
    var provider_org: String = "",
    var provider_org_ref: String = "",
    var receiver_org: String = "",
    var titles: String = "",
    var transaction_value: Double = 0.0
)
