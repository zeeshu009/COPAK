package com.mistercoding.compak.products

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mistercoding.compak.R
import com.mistercoding.compak.databinding.ActivityCarbonCalculatorBinding

class CarbonCalculator : AppCompatActivity() {
    private lateinit var binding: ActivityCarbonCalculatorBinding

    private val questions = arrayOf(
        "What country do you live in?",
        "Would you like to calculate your individual or household footprint?",
        "How many car do you use?",
        "How many short flights do you take each year?",
        "How many long flights do you take each year?",
        "What is your diet like?",
        "How often do you eat red meat?",
        "How often do you have chicken,pork,seafood,or eggs?",
        "How often do you have milk,cheese,or other dairy products?",
        "How big is your living space?",
        "Do you have any pets?",
        "How else do you get around?",
        "How much do you travel by bus each week?",
        "How much do you travel by trasit rail each week?",
        "How much do you travel by intercity rail each week?",
        "How much do you spend on furniture and appliances per month?",
        "How much do you spend on clothes each months?",
        "How much do you spend on all other stuff every month?",
        "How much do you spend on services per month?",
        "How much electricity do you use?",
        "How much renewable energy do you purchase or produce?",
        "How much natural gas do you used?",

        )
    val carQuesitons = arrayOf("What type of car is Car 1?",
        "How many miles per gallon does Car 1 get?",
        "How many miles do you drive each week in Car 1?")

    val carOptions = arrayOf("Gasoline","Hybrid","Electric",
        "Guzzler","Average","More than average",
        "Rarely","Average","more than average")




    val descriptions = arrayOf(
        arrayOf("Entering your country helps us begin the calculation with a baseline for every category."),
        arrayOf("Household emissions include any additional adults or children whose emissions you’d like to calculate and compensate for."),
        arrayOf("You’ll be able to fill out a mileage and usage profile for each car."),
        arrayOf("Count any flights shorter than 3 hours or around 1000km, like flying from San Francisco to Los Angeles."),
        arrayOf("Count any flights longer than 3 hours or much greater than 1000km, like flying from Los Angeles to New York."),

        arrayOf("Even the food you eat has a carbon footprint, but plants have smaller ones."),
        arrayOf("Lamb and beef both count. Think humburgers,steaks,tacos,or even jerky"),
        arrayOf("The food usually have similar carbon footprints."),
        arrayOf("Count each glass of milk, cheese, or yogurt you drink."),
        arrayOf("If you have shared living spaces, divide it by the number of roommates who use it."),
        arrayOf("The majority of a pet's emissions come from their food consumption. Typically, bigger pets have a bigger footprint."),
        arrayOf("Select all the options that apply to you in an average month."),
        arrayOf("Buses are pretty efficient, so its no big deal if your estimate is a little off."),
        arrayOf("Transit is pretty efficient, so its no big deal if your estimate is a little off."),
        arrayOf("For long distance travel, train are the most efficient option."),
        arrayOf("Divide purchases by the number of months they'll be useful for."),
        arrayOf("Think online shopping and trips to the mall."),
        arrayOf("This category includes phones, household supplies, and basically anything else that you can own."),
        arrayOf("Services include health insurance, phone bills, subscriptions, hiring an accountant, etc. Paying a company or someone to do something for you falls under this category"),
        arrayOf("Your electrical bill should tell you how many kWh you used."),
        arrayOf("If you pay extra for renewable energy or you have your own solar panel, mark down how much of your electricity comes from that source."),
        arrayOf("Most bills for natural gas should include the amount you used that month. You can divide the total by the number of people who share that bill.")

    )
    val imges = arrayOf(
        R.drawable.cloudearth,
        R.drawable.cloudfamily,
        R.drawable.cloudcar,
        R.drawable.cloudplanejumbo,
        R.drawable.cloudplanejumbo,
        R.drawable.cloudveggies,
        R.drawable.cloudmeat,
        R.drawable.cloudchicken,
        R.drawable.clouddairy,
        R.drawable.cloudhouse,
        R.drawable.cloudpet,
        R.drawable.cloudbus,
        R.drawable.cloudbus,
        R.drawable.cloudrail,
        R.drawable.cloudintercity,
        R.drawable.cloudcouch,
        R.drawable.cloudclothes,
        R.drawable.cloudsupplies,
        R.drawable.cloudservices,
        R.drawable.cloudbattery,
        R.drawable.cloudrenewables,
        R.drawable.cloudnaturalgas
    )
    val options = arrayOf(
        arrayOf("United States", "United Kingdom", "Germany", "Rest of the world"),
        arrayOf("Just me", "Household"),
        arrayOf("None", "1 car", "2 cars", "3 cars or more"),
        arrayOf("None", "4 roundtrips", "12 round trips", "13 or more"),
        arrayOf("None", "Two roundtrips", "Four roundtrips", "13 or more"),
        arrayOf("Vegetarian", "Non-Vegetarian", "Vegan", "Neither"),
        arrayOf("Never","Couple times a week","Every day","2 or more day"),
        arrayOf("Never","Couple times a week","Every day","2 or more day"),
        arrayOf("Never","Couple times a week","Every day","2 or more day"),
        arrayOf("Small apartment", "Average home", "Large Home", "Largest"),
        arrayOf("Yes", "No"),
        arrayOf("Bus", "Transit Rail", "Inter City Rail", "Walk or Bike"),
        arrayOf("Rarely", "Daily Commute", "Always", "Never"),
        arrayOf("Rarely", "Daily Commute", "Always", "Never"),
        arrayOf("Rarely", "Daily Commute", "Always", "Never"),
        arrayOf("A little", "Average", "A lot", "None"),
        arrayOf("A little", "Average", "A lot", "None"),
        arrayOf("A little", "Average", "A lot", "None"),
        arrayOf("A little", "Average", "A lot", "None"),
        arrayOf("A little", "Average", "A lot", "None"),
        arrayOf("None", "Half", "All"),
        arrayOf("None", "Half", "All")
    )


    private var currentQuestionIndex = 0
    private var score :Double = 0.0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarbonCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestions()
        binding.carbonNextButton.setOnClickListener {
            nextButton()
        }
        binding.carbonBackButton.setOnClickListener {
            backButton()
        }
        binding.carbonSkipResults.setOnClickListener {
            skipButton()
        }
        binding.carbonAnswer1.setOnClickListener {
            checkAnswer(0)
        }
        binding.carbonAnswer2.setOnClickListener {
            checkAnswer(1)
        }
        binding.carbonAnswer3.setOnClickListener {
            checkAnswer(2)
        }
        binding.carbonAnswer4.setOnClickListener {
            checkAnswer(3)
        }
    }

    private fun correctButtonColor(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.carbonAnswer1.setBackgroundResource(R.color.green)
            1 -> binding.carbonAnswer2.setBackgroundResource(R.color.green)
            2 -> binding.carbonAnswer3.setBackgroundResource(R.color.green)
            3 -> binding.carbonAnswer4.setBackgroundResource(R.color.green)
        }


    }
    private fun resetButtonColor(){
        binding.carbonAnswer1.setBackgroundResource(R.color.white)
        binding.carbonAnswer2.setBackgroundResource(R.color.white)
        binding.carbonAnswer3.setBackgroundResource(R.color.white)
        binding.carbonAnswer4.setBackgroundResource(R.color.white)
    }

    private fun showResults() {
        val intent = Intent(this, Subscription::class.java)
        intent.putExtra("score", score)
        startActivity(intent)
        finish()
    }

    private fun displayQuestions() {
        // Set the question text
        resetButtonColor()
        binding.carbonStatement.text = questions[currentQuestionIndex]
        binding.carbonDescription.text = descriptions[currentQuestionIndex][0]
        binding.carbonImage.setImageResource(imges[currentQuestionIndex])

        // Get the current options
        val currentOptions = options[currentQuestionIndex]

        // Update the EditText views with the available options
        val editTexts = arrayOf(
            binding.carbonAnswer1,
            binding.carbonAnswer2,
            binding.carbonAnswer3,
            binding.carbonAnswer4
        )

        // Loop through the options and set text to the corresponding EditText
        for (i in editTexts.indices) {
            if (i < currentOptions.size) {
                editTexts[i].visibility = View.VISIBLE
                editTexts[i].setText(currentOptions[i])
            } else {
                editTexts[i].visibility = View.GONE
            }
        }
    }
    private fun checkAnswer(selectedAnswerIndex: Int) {
        val question = questions[currentQuestionIndex]

        // Calculate score based on question type and selected answer
        score += when (question) {
            "What country do you live in?" -> getCountryCO2(selectedAnswerIndex)
            "Would you like to calculate your individual or household footprint?" -> getHouseholdMultiplier(selectedAnswerIndex)
            "How many car do you use?" -> getCarCO2(selectedAnswerIndex)
            "How many short flights do you take each year?" -> getFlightCO2(selectedAnswerIndex, isShortFlight = true)
            "How many long flights do you take each year?" -> getFlightCO2(selectedAnswerIndex, isShortFlight = false)
            "What is your diet like?" -> getDietCO2(selectedAnswerIndex)
            "How often do you eat red meat?" -> getRedMeatCO2(selectedAnswerIndex)
            "How often do you have chicken, pork, seafood, or eggs?" -> getProteinCO2(selectedAnswerIndex)
            "How often do you have milk, cheese, or other dairy products?" -> getDairyCO2(selectedAnswerIndex)
            "How big is your living space?" -> getLivingSpaceCO2(selectedAnswerIndex)
            "Do you have any pets?" -> getPetsCO2(selectedAnswerIndex)
            "How else do you get around?" -> getTransportCO2(selectedAnswerIndex)
            "How much do you travel by bus each week?" -> getBusCO2(selectedAnswerIndex)
            "How much do you travel by transit rail each week?" -> getTransitRailCO2(selectedAnswerIndex)
            "How much do you travel by intercity rail each week?" -> getIntercityRailCO2(selectedAnswerIndex)
            "How much do you spend on furniture and appliances per month?" -> getFurnitureAppliancesCO2(selectedAnswerIndex)
            "How much do you spend on clothes each month?" -> getClothesCO2(selectedAnswerIndex)
            "How much do you spend on all other stuff every month?" -> getOtherStuffCO2(selectedAnswerIndex)
            "How much do you spend on services per month?" -> getServicesCO2(selectedAnswerIndex)
            "How much electricity do you use?" -> getElectricityCO2(selectedAnswerIndex)
            "How much renewable energy do you purchase or produce?" -> getRenewableEnergyOffset(selectedAnswerIndex)
            "How much natural gas do you use?" -> getNaturalGasCO2(selectedAnswerIndex)
            else -> 0.0
        }

        // Change the color of the correct button
        correctButtonColor(selectedAnswerIndex)

        // Proceed to the next question or show results
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            // Delay to allow the user to see the correct answer
            Handler(Looper.getMainLooper()).postDelayed({
                displayQuestions()
            }, 1000)
        } else {
            showResults()
        }
    }

    // Functions for calculating CO₂ values based on the input
    private fun getCountryCO2(index: Int): Double {
        return when (index) {
            0 -> 16.0 // United States
            1 -> 5.5 // United Kingdom
            2 -> 8.9 // Germany
            else -> 4.5 // Rest of the world
        }
    }

    private fun getHouseholdMultiplier(index: Int): Double {
        // Assuming this multiplies the score based on the household size
        return when (index) {
            0 -> 1.0 // Just me
            1 -> 2.0 // This should be dynamically determined
            else -> 1.0
        }
    }

    private fun getCarCO2(index: Int): Double {
        // Add logic to calculate car-related CO₂ emissions
        return 1.0// Placeholder
    }

    private fun getFlightCO2(index: Int, isShortFlight: Boolean): Double {
        return if (isShortFlight) {
            when (index) {
                0 -> 0.0
                1 -> 1.2 // 4 round trips
                2 -> 3.6 // 12 round trips
                else -> 3.9 // 13 or more
            }
        } else {
            when (index) {
                0 -> 0.0
                1 -> 3.2 // 2 round trips
                2 -> 6.4 // 4 round trips
                else -> 20.8 // 13 or more
            }
        }
    }

    private fun getDietCO2(index: Int): Double {
        return when (index) {
            0 -> 1.7 // Vegetarian
            1 -> 3.3 // Non-Vegetarian
            2 -> 1.5 // Vegan
            else -> 2.5 // Neither
        }
    }

    private fun getRedMeatCO2(index: Int): Double {
        return when (index) {
            0 -> 0.0 // Never
            1 -> 0.7 // Couple times a week
            2 -> 1.4 // Every day
            else -> 2.8 // 2 or more per day
        }
    }

    private fun getProteinCO2(index: Int): Double {
        return when (index) {
            0 -> 0.0
            1 -> 0.5
            2 -> 1.0
            else -> 2.0
        }
    }

    private fun getDairyCO2(index: Int): Double {
        return when (index) {
            0 -> 0.0
            1 -> 0.5
            2 -> 1.0
            else -> 2.0
        }
    }

    private fun getLivingSpaceCO2(index: Int): Double {
        return when (index) {
            0 -> 1.5
            1 -> 3.0
            2 -> 6.0
            else -> 10.0
        }
    }

    private fun getPetsCO2(index: Int): Double {
        return when (index) {
            0 -> 1.0 // Yes
            1 -> 0.0 // No
            else -> 0.0
        }
    }

    private fun getTransportCO2(index: Int): Double {
        return when (index) {
            0 -> 0.1 // Bus
            1 -> 0.05 // Transit Rail
            2 -> 0.05 // Inter City Rail
            else -> 0.0 // Walk or Bike
        }
    }

    private fun getBusCO2(index: Int): Double {
        return when (index) {
            0 -> 2.6
            1 -> 7.8
            2 -> 15.6
            else -> 0.0
        }
    }

    private fun getTransitRailCO2(index: Int): Double {
        return when (index) {
            0 -> 2.6
            1 -> 7.8
            2 -> 15.6
            else -> 0.0
        }
    }

    private fun getIntercityRailCO2(index: Int): Double {
        return when (index) {
            0 -> 2.6
            1 -> 7.8
            2 -> 15.6
            else -> 0.0
        }
    }

    private fun getFurnitureAppliancesCO2(index: Int): Double {
        return when (index) {
            0 -> 0.3
            1 -> 0.6
            2 -> 1.2
            else -> 0.0
        }
    }

    private fun getClothesCO2(index: Int): Double {
        return when (index) {
            0 -> 0.3
            1 -> 0.6
            2 -> 1.2
            else -> 0.0
        }
    }

    private fun getOtherStuffCO2(index: Int): Double {
        return when (index) {
            0 -> 0.3
            1 -> 0.6
            2 -> 1.2
            else -> 0.0
        }
    }

    private fun getServicesCO2(index: Int): Double {
        return when (index) {
            0 -> 0.3
            1 -> 0.6
            2 -> 1.2
            else -> 0.0
        }
    }

    private fun getElectricityCO2(index: Int): Double {
        // Implement calculation based on kWh usage and energy source
        return 0.0 // Placeholder
    }

    private fun getRenewableEnergyOffset(index: Int): Double {
        return when (index) {
            0 -> 0.0 // None
            1 -> 0.5 // Half
            2 -> 1.0 // All
            else -> 0.0
        }
    }

    private fun getNaturalGasCO2(index: Int): Double {
        // Implement calculation based on natural gas usage
        return 0.05 // Placeholder
    }






    private fun skipButton() {
        // Logic to skip the current question
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            displayQuestions()
        }
    }

    private fun backButton() {
        // Logic to go back to the previous question
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--
            displayQuestions()
        }
    }

    private fun nextButton() {
        // Logic to move to the next question without answering
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            displayQuestions()
        } else {
            showResults()
        }
    }
}