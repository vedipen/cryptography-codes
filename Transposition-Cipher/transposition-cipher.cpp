#include<bits/stdc++.h>

#define ll long long
#define pl pair<l,l>
#define pll pair<ll,ll>
#define mp make_pair
#define pb push_back
#define F first
#define S second
#define MOD 1000000007
#define vll vector<ll>

using namespace std;

int main() {
	string s;
	cout<<"Enter String : "<<endl;
	cin>>s;
	cout<<"Enter Size : "<<endl;
	ll n;
	cin>>n;
	ll col=s.size()/n;
	vector< vector<string> > v(n, vector<string> (col," "));
	ll k=0;
	for(ll j=0;j<col;j++) {
		for(ll i=0;i<n;i++) {
			v[i][j]=(s[k]);
			cout<<i<<" "<<j<<endl;
			k++;
			if(k==s.size()) break;	
		}
		if(k==s.size()) break;
	}
	for(ll j=0;j<col;j++) {
                for(ll i=0;i<n;i++) {
                        cout<<v[i][j]<<" ";
                }
                cout<<endl;
        }
	cout<<"Enter Encryption Key of length "<<n<<" : (Eg. For n=5, key = 32154) "<<endl;
	string key="";
	cin>>key;
	string enKey="";
	for(ll i=0;i<col;i++) {
		for(ll j=0;j<n;j++) {
			enKey+=v[(ll)(key[j]-'0')-1][i];
		}
	}
	cout<<"Encrypted Message is - "<<endl;
	cout<<enKey<<endl;
	cout<<endl;
	//DECRYPTION
	cout<<"Enter key for decrytion (Length "<<n<<"): "<<endl;
	string deKey;
	cin>>deKey;
	k=0;
	for(ll j=0;j<col;j++) {
                for(ll i=0;i<n;i++) {
                        v[i][j]=(enKey[k]);
                        cout<<i<<" "<<j<<endl;
                        k++;
                        if(k==enKey.size()) break;
                }
                if(k==enKey.size()) break;
        }
	for(ll j=0;j<col;j++) {
		for(ll i=0;i<n;i++) {
			cout<<v[i][j]<<" ";
		}
		cout<<endl;
	}
	string finalDe="";
	for(ll i=0;i<col;i++) {
                for(ll j=0;j<n;j++) {
                        finalDe+=v[(ll)(deKey[j]-'0')-1][i];
                }
        }
	cout<<finalDe<<endl;

	return 0;
}


//vedipen
