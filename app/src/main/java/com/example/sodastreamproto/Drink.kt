package com.example.sodastreamproto

class Drink(
    val drinkIngredients: ArrayList<Pair<Ingredient, Int>>,
    var drinkName: String,
    var drinkPrice: Double,
    var drinkQuantity: Int,
    ) {

    private val maxPumpCountForCollectiveIngredients = 5
    private var currentPumpCountForCollectiveIngredient = 0
    var drinkID: Int = 0

    init{
        drinkID =
            if(Basket.basketDrinks.isNotEmpty()){
                Basket.basketDrinks.last().drinkID + 1
            } else{
                1
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
        currentPumpCountForCollectiveIngredient -= ingredient.second
        return true
    }

    fun changeIngredientQuantity(ingredient: Ingredient, quantity: Int): Boolean{
        var currentIngredient = getIngredientPair(ingredient)

        if(currentIngredient == null){
            addIngredient(ingredient, quantity)
            return true
        }else{
            if(currentIngredient.second + quantity > 5)
                return false
            if(currentIngredient.second + quantity <= 0){
                removeIngredient(ingredient)
                return true
            }

            removeIngredient(ingredient)
            addIngredient(ingredient, quantity)

            return true
        }
    }


}

