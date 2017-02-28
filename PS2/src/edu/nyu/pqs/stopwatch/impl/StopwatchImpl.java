package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.Stopwatch;

public class StopwatchImpl implements Stopwatch {
  protected String ID;
  private boolean isRunning = false;
  protected List<Long> lapTimes = new ArrayList<Long>();
  private Long startTime = 0l;
  
  /**
   * Constructor with parameter
   * @param ID the ID of the stop watch
   */
  public StopwatchImpl(String ID) {
    synchronized(this) {
      this.ID = ID;
    }
  }

  @Override
  public String getId() {
    // TODO Auto-generated method stub
    return ID;
  } 

  @Override
  public void start() throws IllegalStateException {
    // TODO Auto-generated method stub
    synchronized( this ) {
      if( isRunning ) {
        throw new IllegalStateException( "The stopwatch is already running." );
      }
      isRunning = true;
      startTime = System.currentTimeMillis();
    }      
  }

  @Override
  public void lap() throws IllegalStateException {
    // TODO Auto-generated method stub
    synchronized( this ) {
      if( !isRunning ) {
        throw new IllegalStateException( "The stopwatch is supposed to be running when pressing lap.");
      }
      long now = System.currentTimeMillis();
      lapTimes.add( now - startTime );
      startTime = now;
    }
  }

  @Override
  public void stop() throws IllegalStateException {
    // TODO Auto-generated method stub
    synchronized( this ) {
      if( !isRunning ) {
        throw new IllegalStateException( "The stopwatch is supposed to be running when pressing stop.");
      }
      long now = System.currentTimeMillis();
      lapTimes.add( now - startTime );
      startTime = new Long(0);
    }
  }

  @Override
  public void reset() {
    // TODO Auto-generated method stub
    synchronized( this ) {
      startTime = 0l;
      lapTimes.clear();
    }
  }

  @Override
  public List<Long> getLapTimes() {
    // TODO Auto-generated method stub
    synchronized( this ) {
      return lapTimes;
    }
  }
    
  @Override
  public boolean equals( Object o ) {
    if( o == null ) {
      return false;
    }
    if( !(o instanceof StopwatchImpl ) ) {
      return false;
    }
    StopwatchImpl stopwatch = (StopwatchImpl) o;
    return ( getId() == stopwatch.getId() || ( ID != null && ID.equals(stopwatch.getId())));
  }
    
  @Override 
  public int hashCode() {
    int result = 17;
    result = 31 * result + ID.hashCode();
    return result;
  }
  
  /**
   * Returns a list of lap times. The format is as follows:
   * 
   * ID XXX
   * [X.XX, X.XX, ..., X.XX ]
   * @return the ID of the stop watch and a list of lap times seconds
   */
  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();
    output.append( ID ).append( "\n[" );
    for( int i = 0; i < lapTimes.size()-1; ++i ) {
      output.append( String.format("%.2f", lapTimes.get(i) / 1000.0)).append(", " );
    }
    output.append( String.format("%.2f", lapTimes.get(lapTimes.size()-1) / 1000.0)).append("]" );
    return output.toString();
  }
}
