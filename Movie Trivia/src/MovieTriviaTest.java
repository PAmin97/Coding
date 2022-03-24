import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import file.MovieDB;


class MovieTriviaTest {
	
	//instance of movie trivia object to test
		MovieTrivia mt;
		//instance of movieDB object
		MovieDB movieDB = new MovieDB();
		
		@BeforeEach
		void setUp() throws Exception {
			//initialize movie trivia object
			mt = new MovieTrivia ();
			
			//set up movie trivia object
			mt.setUp("moviedata.txt", "movieratings.csv");
			
			//set up movieDB object
			movieDB.setUp("moviedata.txt", "movieratings.csv");
		}

		@Test
		void testSetUp() { 
			assertEquals(6, movieDB.getActorsInfo().size());
			assertEquals(7, movieDB.getMoviesInfo().size());
			
			assertEquals("meryl streep", movieDB.getActorsInfo().get(0).getName());
			assertEquals(3, movieDB.getActorsInfo().get(0).getMoviesCast().size());
			assertEquals("doubt", movieDB.getActorsInfo().get(0).getMoviesCast().get(0));
			
			assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName());
			assertEquals(79, movieDB.getMoviesInfo().get(0).getCriticRating());
			assertEquals(78, movieDB.getMoviesInfo().get(0).getAudienceRating());
		}
		
		@Test
		void testInsertActor () {
			mt.insertActor("test1", new String [] {"testmovie1", "testmovie2"}, movieDB.getActorsInfo());
			assertEquals(7, movieDB.getActorsInfo().size());	
			assertEquals("test1", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getName());
			assertEquals(2, movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().size());
			assertEquals("testmovie1", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().get(0));
			
			// test if the method will just update an existing actors movie list
			mt.insertActor("brad pitt", new String [] {"oceans 10", "oceans 11"}, movieDB.getActorsInfo());
			assertEquals("oceans 10", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 2).getMoviesCast().get(2));
			assertEquals("oceans 11", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 2).getMoviesCast().get(3));
			assertEquals(7, movieDB.getActorsInfo().size());
			// test if method will trim white space and add actor to arraylist
			mt.insertActor("   batman  ", new String [] {"the dark knight", "lego batman movie"}, movieDB.getActorsInfo());
			assertEquals("the dark knight", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().get(0));
			assertEquals("lego batman movie", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().get(1));
			assertEquals(8, movieDB.getActorsInfo().size());
			//test if actors are converted to lower-case and movies are added to their move list
			mt.insertActor("BRANDON KRAKOWSKY", new String [] {"The MAN", "THE myth", "ThE LegenD"}, movieDB.getActorsInfo());
			assertEquals("the man", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 5).getMoviesCast().get(0));
			assertEquals("the myth", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 5).getMoviesCast().get(1));
			assertEquals("the legend", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 5).getMoviesCast().get(2));
			assertEquals(8, movieDB.getActorsInfo().size());
		}
		
		@Test
		void testInsertRating () {
			mt.insertRating("testmovie", new int [] {79, 80}, movieDB.getMoviesInfo());
			assertEquals(8, movieDB.getMoviesInfo().size());	
			assertEquals("testmovie", movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getName());
			assertEquals(79, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getCriticRating());
			assertEquals(80, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getAudienceRating());
			
			// update an existing movies scores
			mt.insertRating("arrival", new int [] {94, 96}, movieDB.getMoviesInfo());
			assertEquals(8, movieDB.getMoviesInfo().size());	
			assertEquals("arrival", movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 7).getName());
			assertEquals(94, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 7).getCriticRating());
			assertEquals(96, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 7).getAudienceRating());
			// test case sensitivity and whitespace
			mt.insertRating("   the DARK knight   ", new int [] {97, 100}, movieDB.getMoviesInfo());
			assertEquals(9, movieDB.getMoviesInfo().size());	
			assertEquals("the dark knight", movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getName());
			assertEquals(97, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getCriticRating());
			assertEquals(100, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getAudienceRating());
			// test if rating inputs are invalid and returns nothing, so nothing changes to the list
			mt.insertRating("interstellar", new int [] {86, 105}, movieDB.getMoviesInfo());
			// the list size is still 9, meaning interstellar was not added
			assertEquals(9, movieDB.getMoviesInfo().size());	
			assertEquals("the dark knight", movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getName());
			assertEquals(97, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getCriticRating());
			assertEquals(100, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getAudienceRating());
		}
		
		@Test
		void testSelectWhereActorIs () {
			assertEquals(3, mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).size());
			assertEquals("doubt", mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).get(0));
			
			// test if an empty list is returned when a non-existent actor is searched for
			assertEquals(0, mt.selectWhereActorIs("clark kent", movieDB.getActorsInfo()).size());
			// add a new actor into actorsInfo and return their first movie in the list
			mt.insertActor("   batman  ", new String [] {"the dark knight", "lego batman movie"}, movieDB.getActorsInfo());
			assertEquals("the dark knight", mt.selectWhereActorIs("batman", movieDB.getActorsInfo()).get(0));
			// check if inputs are converted to lower-case
			assertEquals(0, mt.selectWhereActorIs("BRANDON KRAKOWSKY", movieDB.getActorsInfo()).size());
			// check if white space is trimmed
			assertEquals(3, mt.selectWhereActorIs("     tom hanks     ", movieDB.getActorsInfo()).size());
		}
		
		@Test
		void testSelectWhereMovieIs () {
			assertEquals(2, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).size());
			assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("meryl streep"));
			assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("amy adams"));
			
			// test if an empty list is returned when a non existent movie is searched for
			assertEquals(0, mt.selectWhereMovieIs("brandon krakowsky origin story", movieDB.getActorsInfo()).size());
			// test if movies are lower-case
			assertEquals(2, mt.selectWhereMovieIs("DOUBT", movieDB.getActorsInfo()).size());
			// test if whitespace is trimmed
			assertEquals(1, mt.selectWhereMovieIs("    arrival    ", movieDB.getActorsInfo()).size());
			// test if the method will work for a newly inserted actor
			mt.insertActor("Actor", new String [] {"Actor the Movie"}, movieDB.getActorsInfo());
			assertEquals(true, mt.selectWhereMovieIs("actor the movie", movieDB.getActorsInfo()).contains("actor"));
		}
		
		@Test
		void testSelectWhereRatingIs () {
			assertEquals(6, mt.selectWhereRatingIs('>', 0, true, movieDB.getMoviesInfo()).size());
			assertEquals(0, mt.selectWhereRatingIs('=', 65, false, movieDB.getMoviesInfo()).size());
			assertEquals(2, mt.selectWhereRatingIs('<', 30, true, movieDB.getMoviesInfo()).size());
			
			// test if an empty list is returned when rating is not between 0 and 100
			assertEquals(0, mt.selectWhereRatingIs('>', -5, true, movieDB.getMoviesInfo()).size());
			// test if an empty list is returned when the comparison operator is not '<' , '>', or '='
			assertEquals(0, mt.selectWhereRatingIs('?', 73, true, movieDB.getMoviesInfo()).size());
			// test if any movies have a rating greater than 80
			assertEquals(4, mt.selectWhereRatingIs('>', 80, true, movieDB.getMoviesInfo()).size());
			// test if any movies have a rating lower than 32
			assertEquals(2, mt.selectWhereRatingIs('<', 32, true, movieDB.getMoviesInfo()).size());
		}
		
		@Test
		void testGetCoActors () {
			assertEquals(2, mt.getCoActors("meryl streep", movieDB.getActorsInfo()).size());
			assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("tom hanks"));
			assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("amy adams"));
			
			// test if non existent actor returns an empty list
			assertEquals(0, mt.getCoActors("batman", movieDB.getActorsInfo()).size());
			// test if existing actor returns an empty list
			assertEquals(0, mt.getCoActors("brad pitt", movieDB.getActorsInfo()).size());
			// test if input is converted to lower-case
			assertEquals(2, mt.getCoActors("MERYL STREEP", movieDB.getActorsInfo()).size());
			// test if whitespace is trimmed
			assertEquals(0, mt.getCoActors("       brandon krakowsky     ", movieDB.getActorsInfo()).size());
		}
		
		@Test
		void testGetCommonMovie () {
			assertEquals(1, mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).size());
			assertTrue(mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).contains("the post"));
			
			// test if two actors have never worked together, returns an empty list
			assertEquals(0, mt.getCommonMovie("mr. krabs", "tom hanks", movieDB.getActorsInfo()).size());
			// test if same actor was input twice
			assertEquals(4, mt.getCommonMovie("amy adams", "amy adams", movieDB.getActorsInfo()).size());
			// test if actor's names are lower-cased
			assertTrue(mt.getCommonMovie("meryl STREEP", "TOM hanks", movieDB.getActorsInfo()).contains("the post"));
			// test if white space is trimmed
			assertTrue(mt.getCommonMovie("   meryl streep   ", "amy adams ", movieDB.getActorsInfo()).contains("doubt"));
		}
		
		@Test
		void testGoodMovies () {
			assertEquals(3, mt.goodMovies(movieDB.getMoviesInfo()).size());
			assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("jaws"));
			
			// test if seven returns false since neither score is above 85
			assertFalse(mt.goodMovies(movieDB.getMoviesInfo()).contains("seven"));
			// test if et returns true since both scores are above 85
			assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("et"));
			// test if rocky ii returns true since both scores are above 85
			assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("rocky ii"));
			// test if popeye returns false since both scores are below 85
			assertFalse(mt.goodMovies(movieDB.getMoviesInfo()).contains("popeye"));
		}
		
		@Test
		void testGetCommonActors () {
			assertEquals(1, mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).size());
			assertTrue(mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).contains("meryl streep"));
			
			// test if empty list is returned, when both movies have no actors in common
			assertEquals(0, mt.getCommonActors("doubt", "et", movieDB.getActorsInfo()).size());
			// test if input is lower-cased
			assertEquals(1, mt.getCommonActors("CAST AWAY", "the post", movieDB.getActorsInfo()).size());
			// test if input is trimmed
			assertTrue(mt.getCommonActors("   fight club", "seven   ", movieDB.getActorsInfo()).contains("brad pitt"));
			// test if all actors in the same movie are returned
			assertEquals(2, mt.getCommonActors("doubt", "doubt", movieDB.getActorsInfo()).size());
		}
		
		@Test
		void testGetMean () {
			// test if the size of the array is 2
			assertEquals(2, MovieTrivia.getMean(movieDB.getMoviesInfo()).length);
			// test if the mean critic score is equal to the actual mean
			assertEquals(475/7.0, MovieTrivia.getMean(movieDB.getMoviesInfo())[0]);
			// test if the mean audience score is equal to the actual mean
			assertEquals(460/7.0, MovieTrivia.getMean(movieDB.getMoviesInfo())[1]);
			// add another movie and get the new mean
			mt.insertRating("   the DARK knight   ", new int [] {97, 100}, movieDB.getMoviesInfo());
			assertEquals(572/8.0, MovieTrivia.getMean(movieDB.getMoviesInfo())[0]);
			assertEquals(560/8.0, MovieTrivia.getMean(movieDB.getMoviesInfo())[1]);
		}
}
