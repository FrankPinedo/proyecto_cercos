package servicio;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

public class TipoCambioService {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public Optional<Double> obtenerCambio(String base, String destino) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(API_URL + base);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return Optional.empty();
            }

            try (InputStream inputStream = connection.getInputStream();
                 JsonReader reader = Json.createReader(inputStream)) {
                JsonObject response = reader.readObject();
                JsonObject rates = response.getJsonObject("rates");
                if (rates != null && rates.containsKey(destino)) {
                    JsonNumber rate = rates.getJsonNumber(destino);
                    return Optional.ofNullable(rate).map(JsonNumber::doubleValue);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return Optional.empty();
    }

    public double convertir(double monto, double tipoCambio) {
        return monto * tipoCambio;
    }
}
