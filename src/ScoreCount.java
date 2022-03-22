    public class ScoreCount  {

        private int[][] cumulScores;
        private int bowlIndex;
        public ScoreCount(int bowlVal) {
            bowlIndex = bowlVal;
        }
        public void setBowlIndex(int val) {
            bowlIndex = val;
        }
        public void reset(int partySize) {
            cumulScores = new int[partySize][10];
            for(int i=0;i<partySize;i++)
            {
            	for(int j=0;j<10;j++)
            	{
            		cumulScores[i][j]=0;
            	}
            }
            
        }
        public int getFinalScore() {
            return cumulScores[bowlIndex][9];
        }
        public int [][] getCumulScores() {
            return cumulScores;
        }
        public void setCumulScores(int[][] cumScore) {
            cumulScores = cumScore;
        }
        public int getScore( Bowler Cur, int frame, int ball, int[] curScore) {
            int totalScore = 0;
            for (int i = 0; i != 10; i++){
                cumulScores[bowlIndex][i] = 0;
            }
            int current = 2*(frame - 1)+ball-1;
           
            for (int i = 0; i != current+2; i++){
            	 
            	if( i%2==1 && curScore[i-1]== -2 && curScore[i]==-2 && i<19)
            	{
            		 if(i==1)
            		 {
            			 if(i<current-1)
            			 {
            			  double score=(0.5)*(curScore[i+1]);
            			  cumulScores[bowlIndex][(i/2)]-=(int)score;
            			 }
            		 }
            		 else
            		 {
            			 int cell=(i/2)-1;
            			 int maxSum=0;
            			 for(int j=1;j<=cell;j++)
            			 {
            				 if((cumulScores[bowlIndex][j]-cumulScores[bowlIndex][j-1])>maxSum)
            				 {
            					 maxSum=cumulScores[bowlIndex][j]-cumulScores[bowlIndex][j-1];
            				 }
            			 }
            			 double score1=(0.5)*(maxSum);
            			 cumulScores[bowlIndex][(i/2)]-=(int)score1;
            		 }
            	}
                //Spare:
            	else if( i%2 == 1 && curScore[i - 1] + curScore[i] == 10 && i < current - 1 && i < 19){
                    
                    cumulScores[bowlIndex][(i/2)] += curScore[i+1] + curScore[i];
                } else if( i < current && i%2 == 0 && curScore[i] == 10  && i < 18){
                    
                    if (curScore[i+2] != -1 && (curScore[i+3] != -1 || curScore[i+4] != -1)) {
                        //Ok, got it.
                        strike(curScore, i);
                    } else {
                        break;
                    }
                } else {
                    
                    normal(curScore, i);
                }
            }
            return totalScore;
        }

        private void normal(int[] curScore, int i) {
            if(i < 18){
                if(i % 2 ==0 ) {
                    if (i == 0) {
                    	if(curScore[i]!=-2)
                    	{
                          cumulScores[bowlIndex][i / 2] += curScore[i];
                    	}
                    	else
                    	{
                    	  cumulScores[bowlIndex][i/2]+=0;
                    	}
                    } else {
                        
                        int add = (curScore[i] != -2) ? curScore[i] : 0;
                        cumulScores[bowlIndex][i / 2] += cumulScores[bowlIndex][i / 2 - 1] + add;
                    }
                } else if(curScore[i] != -1 && i >= 1)
                  {
                	 int add = (curScore[i] != -2) ? curScore[i] : 0;
                	 cumulScores[bowlIndex][i/2] += add;
                	
                  }
                                }
            else if ((i/2 == 9 || i/2 == 10)){
            	int add = (curScore[i] != -2) ? curScore[i] : 0;
                cumulScores[bowlIndex][9] += add;
            }
            if (i == 18){
                cumulScores[bowlIndex][9] += cumulScores[bowlIndex][8];
            }
        }

        private void strike(int[] curScore, int i) {
           
            cumulScores[bowlIndex][i/2] += 10;
            if(curScore[i+1] != -1) {
            	int add = (curScore[i+1] != -2) ? curScore[i+1] : 0;
                cumulScores[bowlIndex][i/2] += add + cumulScores[bowlIndex][(i/2)-1];
                if (curScore[i+2] != -1 && curScore[i+2] != -2){
                	 add = (curScore[i+2] != -2) ? curScore[i+2] : 0;
                    cumulScores[bowlIndex][(i/2)] += add;
                } else if(curScore[i+3] != -2){
                	 add = (curScore[i+3] != -2) ? curScore[i+3] : 0;
                    cumulScores[bowlIndex][(i/2)] += add;
                }
            } else {
                int add = (i/2 > 0) ? cumulScores[bowlIndex][(i/2)-1] : 0;
                int add1 = (curScore[i+2] != -2) ? curScore[i+2] : 0;
                cumulScores[bowlIndex][i/2] += add1 + add;
                int id = 4;
                if (curScore[i+3] != -1 &&  curScore[i+3] != -2){
                    id = 3;
                }
                cumulScores[bowlIndex][(i/2)] += curScore[i+id];
            }
        }

    }

