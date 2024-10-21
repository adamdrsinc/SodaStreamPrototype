package com.example.sodastreamproto

class Drink(
    var drinkIngredients: ArrayList<Pair<Ingredient, Int>>,
    var drinkName: String,
    var drinkPrice: Double,
    var drinkQuantity: Int,
    var iceQuantity: Int = 0,
    val isCustom: Boolean,
    var baseDrink: String? = null
    ) {

    private val maxPumpCountForCollectiveIngredients = 5
    private var currentPumpCountForCollectiveIngredient = 0
    var drinkID: Int = 0

    init{
        drinkID =
            if(Basket.getDrinks().isNotEmpty()){
                Basket.getDrinks().last().drinkID + 1
            } else{
                1
            }

        for(drinkIngredient in drinkIngredients){
            currentPumpCountForCollectiveIngredient += drinkIngredient.second
        }
    }

    private fun getIngredientPair(ingredientName: Ingredient): Pair<Ingredient, Int>?{
        for(i in 0 until drinkIngredients.size){
            if(drinkIngredients[i].first == ingredientName)
                return drinkIngredients[i]
        }

        return null
    }

    fun addIngredient(newIngredient: Ingredient, quantity: Int) : Boolean{
        var ingredient = getIngredientPair(newIngredient)
        if(ingredient != null) return false

        drinkIngredients.add(Pair(newIngredient, quantity))
        currentPumpCountForCollectiveIngredient += quantity
        return true
    }

    fun removeIngredient(ingredient: Ingredient): Boolean{
        var ingredient = getIngredientPair(ingredient)
        if(ingredient == null) return false

        drinkIngredients.remove(ingredient)

        return true
    }


    fun decrementIngredient(ingredient: Ingredient): Boolean {
        var currentIngredient = getIngredientPair(ingredient)

        if (currentIngredient == null) return false


        // Iterate through the list backward to prevent index shift issues when modifying
        for (i in drinkIngredients.indices.reversed()) {
            if (drinkIngredients[i].first.ingredientName == ingredient.ingredientName) {
                drinkIngredients[i] = drinkIngredients[i].copy(second = drinkIngredients[i].second - 1)
                currentPumpCountForCollectiveIngredient -= 1
                break
            }
        }

        return true
    }

    fun incrementIngredient(ingredient: Ingredient): Boolean{
        var currentIngredient = getIngredientPair(ingredient)

        if(currentIngredient == null)
            return false


        if(currentPumpCountForCollectiveIngredient == maxPumpCountForCollectiveIngredients)
            return false

        for(i in 0 until drinkIngredients.size){
            if(drinkIngredients[i].first.ingredientName == ingredient.ingredientName){
                drinkIngredients[i] = drinkIngredients[i].copy(second = drinkIngredients[i].second + 1)
                currentPumpCountForCollectiveIngredient += 1
                break
            }
        }

        return true
    }


}

