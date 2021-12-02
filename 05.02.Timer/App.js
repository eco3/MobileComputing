import React, { Component } from 'react';
import {
  SafeAreaView,
  StyleSheet,
  View,
  Text,
} from 'react-native';

export default class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      startTime: new Date(),
      currentTime: new Date(),
      secondsCounter: '0',
    };
  }

  componentDidMount() {
    this.timerID = setInterval(() => {
      let currentTime = new Date();
      this.setState({currentTime});

      let secondsCounter = Math.round((currentTime - this.state.startTime)/1000);
      this.setState({secondsCounter})
    }, 1000);
  }

  componentWillUnmount() {
    clearInterval(this.timerID);
  }

  render() {
      return (
        <SafeAreaView style={styles.container}>
          <View>
            <Text style={styles.counterText}>{this.state.secondsCounter}s</Text>
          </View>
        </SafeAreaView>
      );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  counterText:{
    fontSize: 50,
    color: '#000'
  }
});
