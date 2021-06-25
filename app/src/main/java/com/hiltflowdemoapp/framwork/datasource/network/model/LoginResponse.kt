package com.hiltflowdemoapp.framwork.datasource.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Manjinder Singh on 05,January,2021
 */  
data class LoginResponse(
    val `data`: Data,
    val msg: String,
    val success: Boolean
)

data class Data(
    val patient: Patient,
    val token: String
)

data class Patient(
    val __v: Int,
    val _id: String,
    val basicInfo: BasicInfo,
    val billingAddress: List<Any>,
    val created_at: String,
    val discount: Int,
    val familyMembers: List<Any>,
    val healthInfo: HealthInfo,
    val height: Int,
    val myDoctors: List<Any>,
    val referralId: String,
    val weight: Int
)

data class BasicInfo(
    val _id: String,
    val city: String,
    val country: String,
    val countryCode: String,
    val dob: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val image: String,
    val isEmailVerified: Boolean,
    val isPhoneNumberVerified: Boolean,
    val isUserBlocked: Boolean,
    val language: String,
    val lastName: String,
    val otp: String,
    val password: String,
    val phoneNumber: String
)

data class HealthInfo(
    val _id: String,
    val allergies: Allergies,
    val insuranceInfo: InsuranceInfo,
    val lifestyle: Lifestyle,
    val medicalHistory: MedicalHistory,
    val mentalHealth: MentalHealth,
    val prescribedMedication: PrescribedMedication,
    val typesOfDoctors: TypesOfDoctors
)

data class Allergies(
    val `data`: DataX,
    val isSkip: Boolean
)

data class InsuranceInfo(
    val isSkip: Boolean
)

data class Lifestyle(
    val `data`: List<DataXX>,
    val isSkip: Boolean
)

data class MedicalHistory(
    val `data`: DataXXX,
    val isSkip: Boolean
)

data class MentalHealth(
    val `data`: List<String>,
    val isSkip: Boolean
)

data class PrescribedMedication(
    val `data`: DataXXXX,
    val isSkip: Boolean
)

data class TypesOfDoctors(
    val `data`: List<String>,
    val isSkip: Boolean
)

data class DataX(
    val `file`: String,
    val name: String
)

data class DataXX(
    val Are_you_sexually_active: AreYouSexuallyActive,
    val Do_you_drink_alcohol: DoYouDrinkAlcohol,
    val Do_you_smoke_or_tobacco: DoYouSmokeOrTobacco,
    val Have_you_travelled_outside_of_the_country_in_past_2_months: HaveYouTravelledOutsideOfTheCountryInPast2Months,
    val When_was_your_last_visit_to_the_doctor: WhenWasYourLastVisitToTheDoctor
)

data class AreYouSexuallyActive(
    val value: String
)

data class DoYouDrinkAlcohol(
    val questions: Questions,
    val value: String
)

data class DoYouSmokeOrTobacco(
    val questions: QuestionsX,
    val value: String
)

data class HaveYouTravelledOutsideOfTheCountryInPast2Months(
    val value: String
)

data class WhenWasYourLastVisitToTheDoctor(
    val questions: QuestionsXX,
    val value: String
)

data class Questions(
    val How_often_do_you_Drink: String
)

data class QuestionsX(
    val How_many_years_have_you_been_using_tobacco: String,
    val How_often_do_you_use_or_smoke_tobacco: String
)

data class QuestionsXX(
    val When_was_your_last_visit_to_the_doctor: String
)

data class DataXXX(
    val `file`: String,
    val name: String
)

data class DataXXXX(
    val `file`: String,
    val name: String
)