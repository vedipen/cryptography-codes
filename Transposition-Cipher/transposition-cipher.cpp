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
			k++;
			if(k==s.size()) break;
		}
		if(k==s.size()) break;
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
	cout<<"Enter key for decryption (Length "<<n<<"): "<<endl;
	string deKey;
	cin>>deKey;
	k=0;
	//Rotate cyclicly
	// for(ll i=0;i<deKey.size();i++) {
	// 	deKey[i]=((((ll)(deKey[i]-'0')+1)%n)+1)+'0';
	// }
	for(ll j=0;j<col;j++) {
		for(ll i=0;i<n;i++) {
			v[i][j]=(enKey[k]);
			k++;
			if(k==enKey.size()) break;
		}
		if(k==enKey.size()) break;
	}
	string finalDe="";
	for(ll i=0;i<col;i++) {
		for(ll j=0;j<n;j++) {
			finalDe+=v[(ll)(deKey[j]-'0')-1][i];
		}
	}
	cout<<"Decrypted Message is - "<<endl;
	cout<<finalDe<<endl;
	if(key==deKey) {
		cout<<"Encryption, decryption successful"<<endl;
	} else {
		cout<<"Encryption, decryption unsuccessful"<<endl;
	}
	return 0;
}


//vedipen
