package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.Stopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for Stopwatch objects.
 * It maintains references to all created Stopwatch objects and provides a
 * convenient method for getting a list of those objects.
 *
 */
public class StopwatchFactory {
  
  private static List<Stopwatch> stopwatchList = new ArrayList<Stopwatch>();
  
  /**
   * Creates and returns a new Stopwatch object
   * @param id The identifier of the new object
   * @return The new Stopwatch object
   * @throws IllegalArgumentException if <code>id</code> is empty, null, or
   *     already taken.
   */
  public static Stopwatch getStopwatch(String id) throws IllegalArgumentException {
    // replace this return statement with correct code
    if( id == null )  {
      throw new IllegalArgumentException( "id cannot be null" );
    }
    else if( id.isEmpty() ) {
      throw new IllegalArgumentException( "id cannot be empty" );
    }
    else {
      synchronized( stopwatchList ) {
        StopwatchImpl stopwatch = new StopwatchImpl( id );
        if( stopwatchList.contains( stopwatch ) ) {
          throw new IllegalArgumentException( "id has already taken" );
        }
        else {
          stopwatchList.add( stopwatch );
          return stopwatch;
        }
      }
    }
  }

  /**
   * Returns a list of all created stopwatches
   * @return a List of al creates Stopwatch objects.  Returns an empty
   * list if no Stopwatches have been created.
   */
  public static List<Stopwatch> getStopwatches() {
    return stopwatchList;
  }
}
