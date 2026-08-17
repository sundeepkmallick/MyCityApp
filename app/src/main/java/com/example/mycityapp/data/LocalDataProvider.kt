package com.example.mycityapp.data

import com.example.mycityapp.model.Category
import com.example.mycityapp.model.Place

object LocalDataProvider {

    private val categories = listOf(
        Category(
            id = "cafes",
            name = "Cafés",
            imageUrl = "https://images.unsplash.com/photo-1501339847302-ac426a4a7cbb"
        ),
        Category(
            id = "restaurants",
            name = "Restaurants",
            imageUrl = "https://images.unsplash.com/photo-1515003197210-e0cd71810b5f"
        ),
        Category(
            id = "parks",
            name = "Parks & Nature",
            imageUrl = "https://images.unsplash.com/photo-1473445361085-b9a07f55608b"
        ),
        Category(
            id = "attractions",
            name = "Attractions",
            imageUrl = "https://images.unsplash.com/photo-1560969184-10fe8719e047"
        )
    )

    val defaultCategory: Category = getCategories()[0]

    // ---------------------------------------------------------
    // Places
    // ---------------------------------------------------------

    val places = listOf(

        // =====================================================
        // CAFÉS
        // =====================================================

        Place(
            id = "five_elephant",
            categoryId = "cafes",
            name = "Five Elephant",
            imageUrl = "https://images.unsplash.com/photo-1445116572660-236099ec97a0",
            description = """
                One of Berlin's popular specialty coffee spots.
                Known for excellent coffee and its famous cheesecake.
                A relaxed place to enjoy coffee with friends.
            """.trimIndent(),
            address = "Reichenberger Straße 101, 10999 Berlin",
            rating = 4.6,
            tags = listOf(
                "Coffee",
                "Cake",
                "Cozy"
            )
        ),

        Place(
            id = "the_barn",
            categoryId = "cafes",
            name = "The Barn",
            imageUrl = "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085",
            description = """
                A well-known specialty coffee shop in Berlin.
                Great coffee, pastries and a comfortable atmosphere
                make it a popular destination for coffee lovers.
            """.trimIndent(),
            address = "Auguststraße 58, 10119 Berlin",
            rating = 4.5,
            tags = listOf(
                "Specialty Coffee",
                "Pastries",
                "Breakfast"
            )
        ),

        Place(
            id = "bonanza_coffee",
            categoryId = "cafes",
            name = "Bonanza Coffee",
            imageUrl = "https://images.unsplash.com/photo-1514432324607-a09d9b4aefdd",
            description = """
                Modern specialty coffee shop serving carefully
                prepared espresso and filter coffee.
            """.trimIndent(),
            address = "Oderberger Straße 35, 10435 Berlin",
            rating = 4.5,
            tags = listOf(
                "Coffee",
                "Modern",
                "Filter Coffee"
            )
        ),

        Place(
            id = "five_roasters",
            categoryId = "cafes",
            name = "Five Roasters",
            imageUrl = "https://images.unsplash.com/photo-1509042239860-f550ce710b93",
            description = """
                A relaxed Berlin coffee spot with freshly roasted
                beans, espresso drinks and light snacks.
            """.trimIndent(),
            address = "Berlin, Germany",
            rating = 4.4,
            tags = listOf(
                "Coffee",
                "Roastery"
            )
        ),

        Place(
            id = "cafe_anna_blume",
            categoryId = "cafes",
            name = "Anna Blume",
            imageUrl = "https://images.unsplash.com/photo-1554118811-1e0d58224f24",
            description = """
                A charming café with breakfast, cakes, coffee
                and a beautiful outdoor seating area.
            """.trimIndent(),
            address = "Kollwitzstraße 83, 10435 Berlin",
            rating = 4.3,
            tags = listOf(
                "Breakfast",
                "Cake",
                "Outdoor Seating"
            )
        ),

        // =====================================================
        // RESTAURANTS
        // =====================================================

        Place(
            id = "curry_36",
            categoryId = "restaurants",
            name = "Curry 36",
            imageUrl = "https://images.unsplash.com/photo-1552566626-52f8b828add9",
            description = """
                A famous Berlin street-food destination.
                Try the classic currywurst with fries.
            """.trimIndent(),
            address = "Mehringdamm 36, 10961 Berlin",
            rating = 4.4,
            tags = listOf(
                "Currywurst",
                "Fast Food",
                "Berlin Classic"
            )
        ),

        Place(
            id = "markthalle_neun",
            categoryId = "restaurants",
            name = "Markthalle Neun",
            imageUrl = "https://images.unsplash.com/photo-1555396273-367ea4eb4db5",
            description = """
                A historic market hall with food stalls,
                restaurants and regular food events.
            """.trimIndent(),
            address = "Eisenbahnstraße 42/43, 10997 Berlin",
            rating = 4.5,
            tags = listOf(
                "Food Market",
                "Street Food",
                "Local Food"
            )
        ),

        Place(
            id = "monsieur_vuong",
            categoryId = "restaurants",
            name = "Monsieur Vuong",
            imageUrl = "https://images.unsplash.com/photo-1515003197210-e0cd71810b5f",
            description = """
                A popular Vietnamese restaurant offering fresh
                dishes in a casual and friendly environment.
            """.trimIndent(),
            address = "Alte Schönhauser Straße 46, 10119 Berlin",
            rating = 4.4,
            tags = listOf(
                "Vietnamese",
                "Noodles",
                "Asian Food"
            )
        ),

        Place(
            id = "mustafa_kebab",
            categoryId = "restaurants",
            name = "Mustafa's Gemüse Kebap",
            imageUrl = "https://images.unsplash.com/photo-1529006557810-274b9b2fc783",
            description = """
                One of Berlin's famous kebab spots.
                Known for grilled vegetables, chicken and flavorful sauces.
            """.trimIndent(),
            address = "Mehringdamm 32, 10961 Berlin",
            rating = 4.3,
            tags = listOf(
                "Kebab",
                "Street Food",
                "Vegetables"
            )
        ),

        // =====================================================
        // PARKS
        // =====================================================

        Place(
            id = "tiergarten",
            categoryId = "parks",
            name = "Tiergarten",
            imageUrl = "https://images.unsplash.com/photo-1500534623283-312aade485b7",
            description = """
                Berlin's large central park is perfect for walking,
                cycling, relaxing and enjoying nature in the city.
            """.trimIndent(),
            address = "Str. des 17. Juni, 10557 Berlin",
            rating = 4.8,
            tags = listOf(
                "Nature",
                "Walking",
                "Cycling"
            )
        ),

        Place(
            id = "tempelhofer_feld",
            categoryId = "parks",
            name = "Tempelhofer Feld",
            imageUrl = "https://images.unsplash.com/photo-1500534314209-a25ddb2bd429",
            description = """
                A huge open public space located on the former
                Tempelhof Airport airfield.
            """.trimIndent(),
            address = "Tempelhofer Damm, 12101 Berlin",
            rating = 4.7,
            tags = listOf(
                "Cycling",
                "Walking",
                "Open Space"
            )
        ),

        Place(
            id = "victoriapark",
            categoryId = "parks",
            name = "Viktoriapark",
            imageUrl = "https://images.unsplash.com/photo-1441974231531-c6227db76b6e",
            description = """
                A beautiful park in Kreuzberg featuring a waterfall,
                hilltop views and plenty of green space.
            """.trimIndent(),
            address = "Kreuzbergstraße, 10965 Berlin",
            rating = 4.6,
            tags = listOf(
                "Park",
                "Waterfall",
                "View"
            )
        ),

        // =====================================================
        // ATTRACTIONS
        // =====================================================

        Place(
            id = "brandenburg_gate",
            categoryId = "attractions",
            name = "Brandenburg Gate",
            imageUrl = "https://images.unsplash.com/photo-1560969184-10fe8719e047",
            description = """
                One of Berlin's most famous landmarks and an
                important symbol of the city's history.
            """.trimIndent(),
            address = "Pariser Platz, 10117 Berlin",
            rating = 4.8,
            tags = listOf(
                "Landmark",
                "History",
                "Photography"
            )
        ),

        Place(
            id = "berlin_cathedral",
            categoryId = "attractions",
            name = "Berlin Cathedral",
            imageUrl = "https://images.unsplash.com/photo-1599940824399-b87987ceb72a",
            description = """
                A spectacular historic cathedral located on
                Museum Island in the heart of Berlin.
            """.trimIndent(),
            address = "Am Lustgarten, 10178 Berlin",
            rating = 4.7,
            tags = listOf(
                "Architecture",
                "History",
                "Museum Island"
            )
        ),

        Place(
            id = "museum_island",
            categoryId = "attractions",
            name = "Museum Island",
            imageUrl = "https://images.unsplash.com/photo-1599940824399-b87987ceb72a",
            description = """
                A UNESCO World Heritage site featuring several
                important museums in central Berlin.
            """.trimIndent(),
            address = "Bodestraße 1-3, 10178 Berlin",
            rating = 4.8,
            tags = listOf(
                "Museums",
                "History",
                "Culture"
            )
        ),

        Place(
            id = "east_side_gallery",
            categoryId = "attractions",
            name = "East Side Gallery",
            imageUrl = "https://images.unsplash.com/photo-1564399579883-451a5d44ec08",
            description = """
                A long section of the Berlin Wall covered with
                colorful murals created by artists from around the world.
            """.trimIndent(),
            address = "Mühlenstraße 3-100, 14059 Berlin",
            rating = 4.6,
            tags = listOf(
                "Berlin Wall",
                "Art",
                "History"
            )
        )
    )

    // ---------------------------------------------------------
    // Helper functions
    // ---------------------------------------------------------

    fun getCategories(): List<Category> {
        return categories
    }

    fun getCategory(categoryId: String): Category? {
        return categories.find { it.id == categoryId }
    }

    fun getPlacesByCategory(categoryId: String): List<Place> {
        return places.filter { it.categoryId == categoryId }
    }

    fun getPlace(placeId: String): Place? {
        return places.find { it.id == placeId }
    }
}