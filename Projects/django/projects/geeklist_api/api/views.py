from django.shortcuts import render
from rest_framework import routers, serializers, viewsets
from api.serializers import GeeksSerializer
from api.models import Geeks


# Create your views here.

# ViewSets define the view behavior.
class GeeksViewSet(viewsets.ModelViewSet):
    queryset = Geeks.objects.all().order_by("full_name")
    serializer_class = GeeksSerializer
