class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][]adjMatrix=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i!=j)adjMatrix[i][j]=Integer.MAX_VALUE;
            }
        }
        for(int[]edge:edges){
            adjMatrix[edge[0]][edge[1]]=edge[2];
            adjMatrix[edge[1]][edge[0]]=edge[2];
        }
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(adjMatrix[i][k]==Integer.MAX_VALUE||adjMatrix[k][j]==Integer.MAX_VALUE)continue;
                    adjMatrix[i][j]=Math.min(adjMatrix[i][k]+adjMatrix[k][j],adjMatrix[i][j]);
                }
            }
        }
        int count=0,mincity=-1,minval=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            count=0;
            for(int j=0;j<n;j++){
                if(adjMatrix[i][j]<=distanceThreshold)count++;
            }
            if(count<=minval){
                minval=count;
                mincity=i;
            }
        }
        return mincity;
    }
}