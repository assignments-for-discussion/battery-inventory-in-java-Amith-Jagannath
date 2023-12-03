package bunchbysoh;

public class Main {
  static final int rated_capacity = 120; // Assumed to be 120 initially
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static void classifyBattery(double SoH , CountsBySoH counts){
      if( SoH>80 && SoH<=100 ) counts.healthy++;     // Battery is healthy
      else
      if( SoH>=62 && SoH<=80 ) counts.exchange++;    // Battery needs to be exchanged
      else
      if(SoH<62 && SoH>=0 ) counts.failed++;         // Battery has failed already
      else{                                          // edge case (incorrect input) if present capacity is less than zero or greater than rated capacity
          double  p = rated_capacity*SoH/100;
          System.out.println("\nBattery has a present capacity : " + (int) p+ " which is incorrect\n");
      }
  }
  static double computeSoH(int present_capacity){    
      return ((double)100*present_capacity)/rated_capacity;
  }
  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();
    for(int i=0;i<presentCapacities.length;i++){
      double SoH = computeSoH(presentCapacities[i]);
      classifyBattery(SoH,counts);
    }
    return counts;
  }

  static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");
    int[] presentCapacities = {113, 116, 80, 95, 92, 70,300}; //here is 300 is greater than rated capacity which is incorrect, hence it is being handled 
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    assert(counts.healthy == 2);
    assert(counts.exchange == 3);
    assert(counts.failed == 1);
    System.out.println("Done counting :)\n");
  }

  public static void main(String[] args) {
    testBucketingByHealth();
  }
}
