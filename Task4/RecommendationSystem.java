package Task4;

import java.util.*;

public class RecommendationSystem {

    // Sample data: User-Item Rating Matrix
    private static double[][] ratings = {
            {5, 4, 0, 0, 3},  // User 1 ratings for Item 1, 2, 3, 4, 5
            {4, 0, 0, 2, 1},  // User 2 ratings
            {3, 0, 0, 5, 4},  // User 3 ratings
            {0, 4, 0, 0, 5},  // User 4 ratings
            {2, 0, 0, 0, 3}   // User 5 ratings
    };

    public static void main(String[] args) {
        // Step 1: Calculate Item Similarity (Cosine Similarity)
        double[][] similarityMatrix = calculateItemSimilarities(ratings);

        // Step 2: Generate recommendations for a user (e.g., User 1)
        int userIndex = 0; // User 1 (index 0)
        int numberOfRecommendations = 3;
        List<Integer> recommendations = recommendItems(userIndex, similarityMatrix, numberOfRecommendations);

        System.out.println("Recommended items for User " + (userIndex + 1) + ":");
        for (int item : recommendations) {
            System.out.println("Item " + (item + 1));
        }
    }

    // Step 1: Calculate item-item similarities (Cosine Similarity)
    private static double[][] calculateItemSimilarities(double[][] ratings) {
        int numItems = ratings[0].length;
        double[][] similarities = new double[numItems][numItems];

        for (int i = 0; i < numItems; i++) {
            for (int j = 0; j < numItems; j++) {
                if (i != j) {
                    similarities[i][j] = cosineSimilarity(ratings, i, j);
                } else {
                    similarities[i][j] = 1.0; // Item with itself has similarity 1
                }
            }
        }
        return similarities;
    }

    // Step 2: Compute cosine similarity between two items
    private static double cosineSimilarity(double[][] ratings, int item1, int item2) {
        double dotProduct = 0;
        double normItem1 = 0;
        double normItem2 = 0;

        for (int user = 0; user < ratings.length; user++) {
            dotProduct += ratings[user][item1] * ratings[user][item2];
            normItem1 += Math.pow(ratings[user][item1], 2);
            normItem2 += Math.pow(ratings[user][item2], 2);
        }

        return dotProduct / (Math.sqrt(normItem1) * Math.sqrt(normItem2));
    }

    // Step 3: Recommend items to a user based on the similarity matrix
    private static List<Integer> recommendItems(int userIndex, double[][] similarityMatrix, int numberOfRecommendations) {
        Map<Integer, Double> itemScores = new HashMap<>();

        for (int i = 0; i < similarityMatrix.length; i++) {
            if (ratings[userIndex][i] == 0) {  // Only recommend items that the user hasn't rated
                double score = 0;
                for (int j = 0; j < similarityMatrix.length; j++) {
                    if (ratings[userIndex][j] != 0) {  // If user has rated item j
                        score += similarityMatrix[i][j] * ratings[userIndex][j];
                    }
                }
                itemScores.put(i, score);
            }
        }

        // Sort items by score in descending order and return the top N
        List<Map.Entry<Integer, Double>> sortedItems = new ArrayList<>(itemScores.entrySet());
        sortedItems.sort((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));

        List<Integer> recommendations = new ArrayList<>();
        for (int i = 0; i < numberOfRecommendations && i < sortedItems.size(); i++) {
            recommendations.add(sortedItems.get(i).getKey());
        }
        return recommendations;
    }
}