
public class webApi {
    public static void main(String[] args) {
        String mathJSEndpointString = "http://api.mathjs.org/v4/";

        // Mathematical expressions to evaluate
        String[] expressions = {
            "2 * 4 * 4",
            "5 / (7 - 5)",
            "sqrt(5^2 - 4^2)",
            "sqrt(-3^2 - 4^2)"
        };

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            for (String expression : expressions) {
                String result = evaluateExpression(httpClient, mathJSEndpointString, expression);
                System.out.println(expression + " => " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String evaluateExpression(CloseableHttpClient httpClient, String endpoint, String expression) throws Exception {
        HttpPost httpPost = new HttpPost(endpoint);
        httpPost.setHeader("Content-Type", "application/json");

        
        String jsonPayload = "{\"expression\":\"" + expression + "\"}";  // JSON payload with the expression
        httpPost.setEntity(new StringEntity(jsonPayload));

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        }

        return "Error"; // Handle error cases
    }
}
