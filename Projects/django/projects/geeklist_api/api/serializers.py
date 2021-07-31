from rest_framework import serializers
from api.models import Geeks


# Serializers define the API representation.
class GeeksSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Geeks
        fields = ('id', 'full_name', 'phone', 'role', 'profile_pic')
