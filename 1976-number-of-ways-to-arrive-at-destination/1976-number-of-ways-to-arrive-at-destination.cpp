#define ll long long
class Solution {
public:
    int countPaths(int n, vector<vector<int>>& roads) {
        vector<pair<ll,ll>>adjList[n];
        priority_queue<pair<ll,ll>,vector<pair<ll,ll>>,greater<pair<ll,ll>>>pq;
        vector<ll>paths(n,LONG_MAX),ways(n,0);
        for(vector<int>road:roads){
            adjList[road[0]].push_back({road[1],road[2]});
            adjList[road[1]].push_back({road[0],road[2]});
        }
        pq.push({0,0});
        paths[0]=0;
        ways[0]=1;
        int mod=(1e9 + 7);
        while(!pq.empty()){
            ll dist=pq.top().first;
            ll node=pq.top().second;
            pq.pop();
            for(auto it:adjList[node]){
                ll adjNode=it.first;
                ll edW=it.second;
                if(dist+edW<paths[adjNode]){
                    paths[adjNode]=dist+edW;
                    pq.push({paths[adjNode],adjNode});
                    ways[adjNode]=ways[node];
                }
                else if(dist+edW==paths[adjNode])
                    ways[adjNode]=(ways[adjNode]+ways[node])%mod;
            }
        }
        return ways[n-1];
    }
};