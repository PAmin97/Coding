import java.util.ArrayList;

import file.MovieDB;
import movies.Actor;
import movies.Movie;

/**
 * Movie trivia class providing different methods for querying and updating a movie database.
 */
public class MovieTrivia {
	
	/**
	 * Create instance of movie database
	 */
	static MovieDB movieDB = new MovieDB();
	

	public static void main(String[] args) {
		
		//create instance of movie trivia class
				MovieTrivia mt = new MovieTrivia();
				
				
				//setup movie trivia class
				mt.setUp("moviedata.txt", "movieratings.csv");
				
			}
			
			/**
			 * Sets up the Movie Trivia class
			 * @param movieData .txt file
			 * @param movieRatings .csv file
			 */
			public void setUp(String movieData, String movieRatings) {
				//load movie database files
				movieDB.setUp(movieData, movieRatings);
				
				//print all actors and movies
				this.printAllActors();
				this.printAllMovies();		
			}
			
			/**
			 * Prints a list of all actors and the movies they acted in.
			 */
			public void printAllActors () {
				System.out.println(movieDB.getActorsInfo());
			}
			
			/**
			 * Prints a list of all movies and their ratings.
			 */
			public void printAllMovies () {
				System.out.println(movieDB.getMoviesInfo());
			}
			
			
			/**
			 * Inserts given actor and his/her movies into database
			 */
			public void insertActor(String actor, String [] movies, ArrayList<Actor>actorsInfo) {
				boolean addActor = true;
				String cleanActor = actor.trim().toLowerCase();
				for (Actor actr : actorsInfo) {
					if (actr.getName().trim().toLowerCase().equals(cleanActor)) {
						ArrayList <String> listOfMovies = actr.getMoviesCast();
						for (String movie : movies) {
							if (listOfMovies.contains(movie) == false) {
								actr.getMoviesCast().add(movie.trim().toLowerCase());
							}
						}
						addActor = false;
					}
				}
				if (addActor == true) {
					Actor newActor = new Actor(cleanActor);
					for (String movie : movies) {
						newActor.getMoviesCast().add(movie.trim().toLowerCase());
					}
					actorsInfo.add(newActor);
				}
			}
			
			/**
			 * Inserts given rating for given movie into database
			 */
			public void insertRating (String movie, int [] ratings, ArrayList <Movie> moviesInfo) {
				boolean addMovie = true;
				String cleanMovie = movie.trim().toLowerCase();
				if (ratings[0] >= 0 && ratings[0] <= 100 && ratings[1] >= 0 && ratings[1] <= 100 
						&& ratings.length >= 2) {
					for (Movie film : moviesInfo) {
						if (film.getName().trim().toLowerCase().equals(cleanMovie)) {
							film.setCriticRating(ratings[0]);
							film.setAudienceRating(ratings[1]);
							addMovie = false;
						}
					}
					if (addMovie == true ) {
						Movie newMovie = new Movie(cleanMovie, ratings[0], ratings[1]);
						moviesInfo.add(newMovie);
					}
				} else {
					return;
				}
			}
			
			/**
			 * Given an actor, return a list of all movies he/she has been in
			 */
			public ArrayList <String> selectWhereActorIs (String actor, ArrayList <Actor> actorsInfo) {
				ArrayList<String> movieList = new ArrayList<String>();
				String cleanActor = actor.trim().toLowerCase();
				
				for (Actor actr : actorsInfo) {
					String actorsName = actr.getName();
					if (actorsName.equals(cleanActor)) {
						for (String film : actr.getMoviesCast()) {
							movieList.add(film);
						}
					}
				}
				return movieList;
			}
			
			/**
			 * Given a movie, returns the list of all actors in that movie
			 */
			public ArrayList<String> selectWhereMovieIs (String movie, ArrayList <Actor> actorsInfo) {
				ArrayList<String> actorList = new ArrayList<String>();
				String cleanMovie = movie.trim().toLowerCase();
				
				for (Actor actr : actorsInfo) {
					String actorsName = actr.getName();
					for (String film : actr.getMoviesCast()) {
						if (cleanMovie.equals(film)) {
							actorList.add(actorsName);
						}
					}
				}
				return actorList;
			}
			
			/**
			 * returns a list of movies that satisfy an inequality or equality, 
			 * based on the comparison argument and the targeted rating argument
			 */
			public ArrayList <String> selectWhereRatingIs (char comparison, int targetRating, boolean isCritic, ArrayList <Movie> moviesInfo) {
				ArrayList <String> movieList = new ArrayList<String>();
				for (Movie movie : moviesInfo) {
					if (isCritic == true && comparison == '<' && 0 <= targetRating && targetRating <= 100) {
						if (movie.getCriticRating() < targetRating) {
							movieList.add(movie.getName());
						}
					} else if (isCritic == true && comparison == '>' && 0 <= targetRating && targetRating <= 100) {
						if (movie.getCriticRating() > targetRating) {
							movieList.add(movie.getName());
						}
					} else if (isCritic == true && comparison == '=' && 0 <= targetRating && targetRating <= 100) {
						if (movie.getCriticRating() == targetRating) {
							movieList.add(movie.getName());
						}
					}
					if (isCritic == false && comparison == '<' && 0 <= targetRating && targetRating <= 100) {
						if (movie.getAudienceRating() < targetRating) {
							movieList.add(movie.getName());
						}
					} else if (isCritic == false && comparison == '>' && 0 <= targetRating && targetRating <= 100) {
						if (movie.getAudienceRating() > targetRating) {
							movieList.add(movie.getName());
						}
					} else if (isCritic == false && comparison == '=' && 0 <= targetRating && targetRating <= 100) {
						if (movie.getAudienceRating() == targetRating) {
							movieList.add(movie.getName());
						}
					}
				}
				return movieList;
			}
			
			/**
			 * Returns a list of all actors that the given actor has ever worked with in any movie
			 * except the actor herself/himself.
			 */
			public ArrayList <String> getCoActors (String actor, ArrayList <Actor> actorsInfo) {
				String cleanActor = actor.trim().toLowerCase();
				ArrayList <String> coActors = new ArrayList<String>();
				ArrayList <String> movieList = selectWhereActorIs(cleanActor, actorsInfo);
				for (String movie : movieList) {
					ArrayList <String> actorList = selectWhereMovieIs(movie, actorsInfo);
					for (String actr : actorList) {
						if (actr.equals(cleanActor) == false) {
							coActors.add(actr);
						}
					}
				}
				return coActors;
			}
			
			/**
			 * Returns a list of movie names where both actors were cast.
			 */
			public ArrayList <String> getCommonMovie (String actor1, String actor2, ArrayList <Actor> actorsInfo) {
				String cleanActor1 = actor1.trim().toLowerCase();
				String cleanActor2 = actor2.trim().toLowerCase();
				ArrayList <String> commonMoviesList = new ArrayList<String>();
				ArrayList <String> movieList1 = selectWhereActorIs(cleanActor1, actorsInfo);
				ArrayList <String> movieList2 = selectWhereActorIs(cleanActor2, actorsInfo);
				for (String movie1 : movieList1) {
					for (String movie2 : movieList2) {
						if (movie1.equals(movie2)) {
							commonMoviesList.add(movie1);
						}
					}
				}
				return commonMoviesList;
			}
			
			/**
			 * Returns a list of movie names that both critics and the audience have rated above 85 (>= 85)
			 */
			public ArrayList <String> goodMovies (ArrayList <Movie> moviesInfo) {
				ArrayList <String> goodMoviesList = new ArrayList<String>();
				for (Movie movie : moviesInfo) {
					if (movie.getAudienceRating() > 84 && movie.getCriticRating() > 84) {
						goodMoviesList.add(movie.getName());
					}
				}
				return goodMoviesList;
			}
			
			/**
			 * Given a pair of movies, this method returns a list of actors that acted in both movies.
			 */
			public ArrayList <String> getCommonActors (String movie1, String movie2, ArrayList <Actor> actorsInfo) {
				ArrayList <String> commonActorList = new ArrayList<String>();
				String cleanMovie1 = movie1.trim().toLowerCase();
				String cleanMovie2 = movie2.trim().toLowerCase();
				ArrayList <String> actorList1 = selectWhereMovieIs(cleanMovie1, actorsInfo);
				ArrayList <String> actorList2 = selectWhereMovieIs(cleanMovie2, actorsInfo);
				for (String actor1 : actorList1) {
					for (String actor2 : actorList2) {
						if (actor1.equals(actor2)) {
							commonActorList.add(actor1);
						}
					}
				}
				return commonActorList;
			}
			
			/**
			 * Given the moviesInfo DB, this static method returns 
			 * the mean value of the critics’ ratings and the audience ratings.
			 */
			public static double [] getMean (ArrayList <Movie> moviesInfo) {
				double[] meanScoreList = new double[2];
				double totalCritScore = 0.0;
				double totalAudienceScore = 0.0;
				for (Movie movie : moviesInfo) {
					totalCritScore += movie.getCriticRating();
					meanScoreList[0] = totalCritScore / moviesInfo.size();
					totalAudienceScore += movie.getAudienceRating();
					meanScoreList[1] = totalAudienceScore / moviesInfo.size();
				}
				return meanScoreList;
			}
}

