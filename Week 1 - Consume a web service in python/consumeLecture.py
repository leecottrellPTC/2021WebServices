import requests

url = 'https://api.zippopotam.us/us/15204'
#url = 'https://api.zippopotam.us/us/pa/fisher'
#print(url)  #just teting

response = requests.get(url)
if response.status_code == 200:
    print(url + ' is good!!')
    zipJson = response.json()
    #print(zipJson)
else:
    print(url + ' is invalid :(')
    print('Error code ' + str(response.status_code))
    exit(response.status_code)

#parse the JSON
country = zipJson['country']
print(country)
#parts of an array
# city = zipJson['places'][0]['place name']
# lat = zipJson['places'][0]['latitude']
# lon = zipJson['places'][0]['longitude']

#print(city + ' at ' + str(lat) + ', ' + str(lon))
#f('city')
places = zipJson['places']
print(str(len(places)) + ' places found with that name')
# for place in places:
#     city = place['place name']
#     lat = place['latitude']
#     lon = place['longitude']
#     print(f"{city}: ({lat}, {lon})")

for place in places:
    name, lat, lon = place["place name"], place["latitude"], place["longitude"]
    print(f"{name}: ({lat}, {lon})")