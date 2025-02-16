package complete_workflow;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;

public class ClientCredentialsCompleteWorkFlow {
	
	/*
	 * Using this method we simply get our clientId and clientSecret from the Spotify Developer dashboard after registering our app. 
	 * After copying it here, we will add them to our spotifyApi. With this we can search for information that is not related to a 
	 * specific user but, for example, search for an artist by name.
	 * 
	 */
	
	private static final String clientId = "zyuxhfo1c51b5hxjk09x2uhv5n0svgd6g";
	private static final String clientSecret = "zudknyqbh3wunbhcvg9uyvo7uwzeu6nne";

	 private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
	    .setClientId(clientId)
	    .setClientSecret(clientSecret)
	    .build();
	    
	  private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
	    .build();

	  
	  /*
	   * After adding the clientId and clientSecret to spotifyApi, we can make a get token request, 
	   * which we will directly use to fetch an artist search request.
	   * 
	   * parameter: artist - The artist to be searched for.
	   */
		public static void searchAnArtist(String artist) {

			try {
				
				ClientCredentials clientCredentials = clientCredentialsRequest.execute();
				String accessToken = clientCredentials.getAccessToken();
				spotifyApi.setAccessToken(accessToken);

				SearchArtistsRequest getArtist = spotifyApi.searchArtists(artist).build();
				System.out.println(getArtist.execute().getItems()[0].toString());

			} catch (ParseException e) {
				e.printStackTrace();
			} catch (SpotifyWebApiException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		public static void main(String[] args) {
			searchAnArtist("Abba");
		}

	}
