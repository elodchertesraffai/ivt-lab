package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private TorpedoStore MockPrimaryTs;
  private TorpedoStore MockSecondaryTs;
  private GT4500 ship;

  @BeforeEach
  public void init(){
	MockPrimaryTs=mock(TorpedoStore.class);
	MockSecondaryTs=mock(TorpedoStore.class);
    this.ship = new GT4500(MockPrimaryTs, MockSecondaryTs);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
	when(MockPrimaryTs.fire(1)).thenReturn(true);
	when(MockSecondaryTs.fire(1)).thenReturn(true);
	

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(MockPrimaryTs, times(1)).fire(1);
    verify(MockSecondaryTs, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
	when(MockPrimaryTs.fire(1)).thenReturn(true);
	when(MockSecondaryTs.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(MockPrimaryTs, times(1)).fire(1);
    verify(MockSecondaryTs, times(1)).fire(1);
  }
  
    @Test
  public void fireTorpedo_All_PRIMARY_FAIL(){
    // Arrange
	when(MockPrimaryTs.isEmpty()).thenReturn(false);
    // Act
    ship.fireTorpedo(FiringMode.ALL);
  }
  
  @Test
  public void fireTorpedo_All_SECONDARY_FAIL(){
    // Arrange
	when(MockSecondaryTs.isEmpty()).thenReturn(false);
    // Act
    ship.fireTorpedo(FiringMode.ALL);
  }
  
    @Test
  public void fireTorpedo_SINGLE_FAIL(){
    // Arrange
	when(MockPrimaryTs.isEmpty()).thenReturn(false);
	when(MockSecondaryTs.isEmpty()).thenReturn(false);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
  }
  
   @Test
  public void fireTorpedo_SINGLE_SUCCESS1(){
    // Arrange
	when(MockPrimaryTs.isEmpty()).thenReturn(true);
    when(MockSecondaryTs.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
	
	verify(MockSecondaryTs, times(1)).fire(1);
  }
  
   @Test
  public void fireTorpedo_SINGLE_SUCCESS2(){
    // Arrange
	when(MockSecondaryTs.isEmpty()).thenReturn(true);
    when(MockPrimaryTs.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
	
	verify(MockPrimaryTs, times(1)).fire(1);
  }
  
   @Test
  public void fireTorpedo_Single_Fail(){
    // Arrange
	when(MockPrimaryTs.fire(0)).thenReturn(false);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(MockPrimaryTs, times(1)).fire(1);
  }
}
